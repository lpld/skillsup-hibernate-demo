package ua.skillsup.javacourse.bookstore.servlets;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Stream;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.AuthorRepo;
import ua.skillsup.javacourse.bookstore.domain.book.Book;
import ua.skillsup.javacourse.bookstore.domain.genre.Genre;
import ua.skillsup.javacourse.bookstore.domain.genre.GenreRepo;
import ua.skillsup.javacourse.bookstore.domain.publication.Publisher;
import ua.skillsup.javacourse.bookstore.domain.publication.PublisherRepo;

import static java.util.stream.Collectors.toSet;

/**
 * @author leopold
 * @since 29/03/16
 */
@Component
public class DatabaseInitializer {

  @Inject
  private AuthorRepo authorRepo;

  @Inject
  private GenreRepo genreRepo;

  @Inject
  private PublisherRepo publisherRepo;

  @Transactional
  public void initDb() {
    initGenres();
    initPublishers();

    initBooks();

//    initMagazines();
  }

  private void initGenres() {
    Stream.of("Fantasy", "Adventure", "Sci-fi", "Drama", "Utopia")
        .map(Genre::new)
        .forEach(genreRepo::add);
  }

  private void initBooks() {
    final Author author = new Author();
    author.setName("John Ronald Reuel Tolkien");
    author.setBirthday(LocalDate.parse("1892-01-03"));
    author.setCountry("UK");

    authorRepo.add(author);

    final Book book1 = new Book();
    book1.setTitle("The Lord of the Rings");
    book1.setSummary(
        "The Lord of the Rings is an epic high-fantasy novel written by English author J. R. R. Tolkien.");
    book1.setFirstPublished(LocalDate.parse("1954-07-29"));
    book1.setGenres(
        Stream.of("Fantasy", "Adventure")
            .map(genreRepo::getGenre)
            .collect(toSet())
    );

    author.addBook(book1);

    final Publisher publisher1 = publisherRepo.findByName("Publisher1");
    final Publisher publisher2 = publisherRepo.findByName("Publisher2");

    book1.addPublication(publisher1, null, LocalDate.parse("1954-07-29"));
    book1.addPublication(publisher2, null, LocalDate.parse("1954-11-11"));
  }

  private void initPublishers() {
    Stream.of("Publisher1", "Publisher2", "Publisher3")
        .map(Publisher::new)
        .forEach(publisherRepo::add);
  }
}
