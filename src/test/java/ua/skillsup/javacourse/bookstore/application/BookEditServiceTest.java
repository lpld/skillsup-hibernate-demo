package ua.skillsup.javacourse.bookstore.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.SpringConfig;
import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.Book;
import ua.skillsup.javacourse.bookstore.domain.genre.Genre;
import ua.skillsup.javacourse.bookstore.domain.genre.GenreRepo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author leopold
 * @since 13/03/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class BookEditServiceTest {

  @Inject
  private PlatformTransactionManager txManager;

  @Inject
  private BookEditService bookEditService;

  @Inject
  private BookSearchService bookSearchService;

  @Inject
  private SessionFactory sessionFactory;

  @Inject
  private GenreRepo genreRepo;

  @Test
  public void testAddAuthorAndBook() {
    final Author orwell = new Author();
    orwell.setName("George Orwell");
    orwell.setBirthday(LocalDate.parse("1905-02-03"));
    orwell.setCountry("UK");

    bookEditService.createAuthor(
        orwell.getName(),
        orwell.getCountry(),
        orwell.getBirthday()
    );

    final List<Author> list = bookSearchService.findAuthor("Orwell");

    assertEquals(1, list.size());
    final Author orwell2 = list.get(0);
    assertEquals(orwell, orwell2);

    bookEditService.createBook(
        orwell2.getId(), "1984", "bla",
        Stream.of("Science Fiction", "Dystopia").collect(Collectors.toSet())
    );

    final List<Genre> allGenres = doInTransaction(s -> genreRepo.getAllGenres());

    assertEquals(2, allGenres.size());

    assertTrue(allGenres.stream().anyMatch(g -> g.getName().equals("Science Fiction")));
    assertTrue(allGenres.stream().anyMatch(g -> g.getName().equals("Dystopia")));

    doInTransaction(s -> {
      s.refresh(orwell2);

      final List<Book> books = orwell2.getBooks();
      assertEquals(1, books.size());
      return null;
    });

  }

  private <T> T doInTransaction(Function<Session, T> action) {
    final TransactionStatus transaction =
        txManager.getTransaction(new DefaultTransactionDefinition());

    try {
      final T t = action.apply(sessionFactory.getCurrentSession());
      txManager.commit(transaction);
      return t;
    } catch (Throwable e) {
      txManager.rollback(transaction);
      throw e;
    }
  }
}