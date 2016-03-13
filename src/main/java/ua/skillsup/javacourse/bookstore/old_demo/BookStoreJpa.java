package ua.skillsup.javacourse.bookstore.old_demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.Book;
import ua.skillsup.javacourse.bookstore.domain.genre.Genre;

/**
 * @author leopold
 * @since 8/03/16
 */
public class BookStoreJpa {

  private static final Logger log = LoggerFactory.getLogger(BookStoreJpa.class);

  static EntityManagerFactory entityManagerFactory;

  public static void main(String[] args) {
    entityManagerFactory =
        Persistence.createEntityManagerFactory("ua.skillsup.javacourse.BookStore");

    fillGenres();
    createBookAndAuthor();
    authorAddBook();
    updateAuthor();
    deleteGenre();
    lazyCollectionDemo();
    updateBook();
    nPlusOneExample();
    criteriaApiExample();
    optimisticLocking();
    pessimisticLocking();

    entityManagerFactory.close();
  }

  static void fillGenres() {
    log.info("FILL GENRES");
    final EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    Stream.of("Drama", "Classic", "Detective", "Fantasy", "Historical",
              "Horror", "Science fiction", "Mystery")
        .map(Genre::new)
        .forEach(em::persist);

    em.getTransaction().commit();
    em.close();
  }

  static void createBookAndAuthor() {
    log.info("CREATE BOOK AND AUTHOR");
    final EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    final Author author = new Author();
    author.setName("George Orwell");
    author.setCountry("UK");

    final Book book = new Book();
    book.setTitie("Nineteen Eighty-Four");
    book.setSummary(
        "Nineteen Eighty-Four, often published as 1984, is a dystopian novel by English author George Orwell published in 1949.");
    book.setFirstPublished(LocalDate.parse("1949-06-08"));
    book.setAuthor(author);

    author.setBooks(new HashSet<>());
    author.getBooks().add(book);

    em.persist(author);

    log.info("Created new author {} with id = {}", author.getName(), author.getId());

    em.getTransaction().commit();
    em.close();
  }

  static void authorAddBook() {
    log.info("AUTHOR ADD BOOK");
    doInTransaction(em -> {

      final Author orwell = (Author) em
          .createQuery("FROM Author a where a.name=:nm")
          .setParameter("nm", "George Orwell")
          .getSingleResult();

      final Book book = new Book();
      book.setTitie("Animal Farm");
      book.setSummary(
          "Animal Farm is an allegorical and dystopian novella by George Orwell, first published in England on 17 August 1945.");
      book.setFirstPublished(LocalDate.parse("1945-08-17"));
      book.setAuthor(orwell);

      em.persist(book);

      log.info("Created book {} with id = {}", book.getTitie(), book.getId());

      return null;
    });
  }

  static void updateAuthor() {
    log.info("UPDATE AUTHOR");
    final Author orwell = doInTransaction(em -> em.find(Author.class, 9L));

    doInTransaction(em -> {
      orwell.setCountry("USA");

      // looks like there is no session.update alternative
      em.merge(orwell);

      return null;
    });
  }

  static void deleteGenre() {
    log.info("DELETE GENRE");
    doInTransaction(em -> {
      final Genre genre = em.getReference(Genre.class, 2L);

      em.remove(genre);

      return null;
    });
  }

  static void lazyCollectionDemo() {
    log.info("LAZY COLLECTION DEMO");

    final Author author = doInTransaction(em -> em.find(Author.class, 9L));

//    log.info("Author books (outside session): {}", author.getBooks());

    doInTransaction(em -> {
      final Author author2 = em.find(Author.class, 9L);
      log.info("Author books (inside session): {}", author2.getBooks());

      return null;
    });
  }

  static void updateBook() {
    log.info("UPDATE BOOK");
    final Book book = doInTransaction(
        em ->
            (Book) em
                .createQuery("SELECT b FROM Book b WHERE titie=:nm")
                .setParameter("nm", "Animal Farm")
                .getResultList()
                .get(0)
    );

    book.setTitie("123");

    doInTransaction(session -> {
      final Book merged_book = session.merge(book);

      merged_book.setSummary("Abrakadabra");

      return null;
    });
  }

  static void nPlusOneExample() {
    log.info("N+1. Part 1");
    doInTransaction(em -> {
      final List list = em.createQuery("SELECT a FROM Author a")
          .getResultList();

      final List<Author> authors = (List<Author>) list;

      authors.forEach(a -> a.getBooks()
          .forEach(b -> log.info("Book {}", b.getTitie())
          )
      );

      return null;
    });

    log.info("N+1. Part 2");
    doInTransaction(em -> {
      final List list =
          em.createQuery("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books b")
              .getResultList();
      final List<Author> authors = (List<Author>) list;

      authors.forEach(a -> a.getBooks()
          .forEach(b -> log.info("Book {}", b.getTitie())
          )
      );
      return null;
    });
  }

  static void criteriaApiExample() {
    log.info("CRITERIA API - 1");
    doInTransaction(em -> {
      final CriteriaQuery<Object> q = em.getCriteriaBuilder().createQuery();
      final Root<Author> au = q.from(Author.class);
      au.fetch("books");
      q.select(au).distinct(true);
      final List list = em.createQuery(q).getResultList();

      final List<Author> authors = (List<Author>) list;

      authors.forEach(a -> a.getBooks()
          .forEach(b -> log.info("Book {}", b.getTitie())
          )
      );
      return null;
    });

//    log.info("CRITERIA API - 1");
//    doInTransaction(em -> {
//      final CriteriaBuilder cb = em.getCriteriaBuilder();
//      final CriteriaQuery<Object> q = em.getCriteriaBuilder().createQuery();
//      final Root<Book> b = q.from(Book.class);
//      q
//          .select(b)
//          .where(cb.equal(b.get("title"), "Nine%"));
//
//      final List list = em.createQuery(q)
//          .setMaxResults(10)
//          .setFirstResult(0)
//          .getResultList();
//
//      final List<Book> books = (List<Book>) list;
//      log.info("Found books {}", books);
//
//      return null;
//    });
  }

  //
  static void optimisticLocking() {
    log.info("OPTIMISTIC LOCKING");
    final Author author = doInTransaction(em -> em.find(Author.class, 9L));

    log.info("Author version: {}", author.getVersion());

    doInTransaction(em -> {
      final Author a = em.find(Author.class, 9L);
      a.setCountry("Ukraine");
      return null;
    });

    doInTransaction(em -> {
      final Author a = em.find(Author.class, 9L);
      log.info("Author version: {}", a.getVersion());
      return null;
    });

    try {
      doInTransaction(em -> {
        em.merge(author);
        return null;
      });
    } catch (OptimisticLockException se) {
      log.error(se.getMessage(), se);
    }
  }


  static void pessimisticLocking() {
    log.info("PESSIMISTIC LOCKING");
    doInTransaction(em -> {
      final Author author = em.getReference(Author.class, 9L);
      em.lock(author, LockModeType.PESSIMISTIC_WRITE);
      return null;
    });
  }
//
  static <T> T doInTransaction(Function<EntityManager, T> action) {
    final EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();

    try {
      final T result = action.apply(em);
      em.getTransaction().commit();
      return result;
    } catch (Exception ex) {
      em.getTransaction().rollback();
      throw ex;
    } finally {
      em.close();
    }
  }
}
