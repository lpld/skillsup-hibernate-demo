package ua.skillsup.javacourse.bookstore;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import ua.skillsup.javacourse.bookstore.model.Author;
import ua.skillsup.javacourse.bookstore.model.Book;
import ua.skillsup.javacourse.bookstore.model.Genre;

/**
 * @author leopold
 * @since 8/03/16
 */
public class BookStorePureHibernate {

  private static final Logger log = LoggerFactory.getLogger(BookStorePureHibernate.class);

  static SessionFactory sessionFactory;

  public static void main(String[] args) {
    sessionFactory = new Configuration().configure().buildSessionFactory();

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

    sessionFactory.close();
  }

  static void fillGenres() {
    log.info("FILL GENRES");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Stream.of("Drama", "Classic", "Detective", "Fantasy", "Historical",
              "Horror", "Science fiction", "Mystery")
        .map(Genre::new)
        .forEach(session::persist);

    session.getTransaction().commit();
    session.close();
  }

  static void createBookAndAuthor() {
    log.info("CREATE BOOK AND AUTHOR");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

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

    session.persist(author);

    log.info("Created new author {} with id = {}", author.getName(), author.getId());

    session.getTransaction().commit();
    session.close();
  }

  static void authorAddBook() {
    log.info("AUTHOR ADD BOOK");
    doInTransaction(session -> {

      final Author orwell = (Author) session
          .createQuery("FROM Author a where a.name=:nm")
          .setParameter("nm", "George Orwell")
          .uniqueResult();

      final Book book = new Book();
      book.setTitie("Animal Farm");
      book.setSummary(
          "Animal Farm is an allegorical and dystopian novella by George Orwell, first published in England on 17 August 1945.");
      book.setFirstPublished(LocalDate.parse("1945-08-17"));
      book.setAuthor(orwell);

      session.persist(book);

      log.info("Created book {} with id = {}", book.getTitie(), book.getId());

      return null;
    });
  }

  static void updateAuthor() {
    log.info("UPDATE AUTHOR");
    final Author orwell = doInTransaction(s -> s.get(Author.class, 9L));

    doInTransaction(session -> {
      orwell.setCountry("USA");
      session.update(orwell);

      return null;
    });
  }

  static void deleteGenre() {
    log.info("DELETE GENRE");
    doInTransaction(session -> {
      final Genre genre = session.load(Genre.class, 2L);

      session.delete(genre);

      return null;
    });
  }


  static void lazyCollectionDemo() {
    log.info("LAZY COLLECTION DEMO");

    final Author author = doInTransaction(session -> session.get(Author.class, 9L));

//    log.info("Author books (outside session): {}", author.getBooks());

    doInTransaction(session -> {
      final Author author2 = session.get(Author.class, 9L);
      log.info("Author books (inside session): {}", author2.getBooks());

      return null;
    });
  }

  static void updateBook() {
    log.info("UPDATE BOOK");
    final Book book = doInTransaction(
        session ->
            (Book) session
                .createQuery("FROM Book b WHERE titie=:nm")
                .setParameter("nm", "Animal Farm")
                .list()
                .get(0)
    );

    book.setTitie("123");

    doInTransaction(session -> {
      final Book merged_book = (Book) session.merge(book);

      merged_book.setSummary("Abrakadabra");

      return null;
    });
  }

  static void nPlusOneExample() {
    log.info("N+1. Part 1");
    doInTransaction(session -> {
      final List list = session.createQuery("FROM Author a").list();

      final List<Author> authors = (List<Author>) list;

      authors.forEach(a -> a.getBooks()
          .forEach(b -> log.info("Book {}", b.getTitie())
          )
      );

      return null;
    });

    log.info("N+1. Part 2");
    doInTransaction(session -> {
      final List
          list =
          session.createQuery("SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books b").list();
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
    doInTransaction(session -> {
      final List list = session
          .createCriteria(Author.class)
          .setFetchMode("books", FetchMode.JOIN)
          .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
          .list();
      final List<Author> authors = (List<Author>) list;

      authors.forEach(a -> a.getBooks()
          .forEach(b -> log.info("Book {}", b.getTitie())
          )
      );
      return null;
    });

    log.info("CRITERIA API - 1");
    doInTransaction(session -> {
      final List list = session.createCriteria(Book.class)
          .add(Restrictions.ilike("titie", "Nine%"))
          .addOrder(Order.asc("firstPublished"))
          .setFirstResult(0)
          .setMaxResults(10)
          .list();

      final List<Book> books = (List<Book>) list;
      log.info("Found books {}", books);

      return null;
    });
  }

  static void optimisticLocking() {
    log.info("OPTIMISTIC LOCKING");
    final Author author = doInTransaction(session -> session.get(Author.class, 9L));

    log.info("Author version: {}", author.getVersion());

    doInTransaction(session -> {
      final Author a = session.get(Author.class, 9L);
      a.setCountry("Ukraine");
      return null;
    });

    doInTransaction(session -> {
      final Author a = session.get(Author.class, 9L);
      log.info("Author version: {}", a.getVersion());
      return null;
    });

    try {
      doInTransaction(session -> {
        session.update(author);
        return null;
      });
    } catch (StaleStateException se) {
      log.error(se.getMessage(), se);
    }
  }

  static void pessimisticLocking() {
    doInTransaction(session -> {
      final Author author = session.load(Author.class, 9L);
      session.lock(author, LockMode.PESSIMISTIC_WRITE);
      return null;
    });
  }

  static <T> T doInTransaction(Function<Session, T> action) {
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    try {
      final T result = action.apply(session);
      session.getTransaction().commit();
      return result;
    } catch (Exception ex) {
      session.getTransaction().rollback();
      throw ex;
    } finally {
      session.close();
    }
  }
}
