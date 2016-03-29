package ua.skillsup.javacourse.bookstore.persistence;

import org.springframework.stereotype.Repository;

import ua.skillsup.javacourse.bookstore.domain.book.Book;
import ua.skillsup.javacourse.bookstore.domain.book.BookRepo;

/**
 * @author leopold
 * @since 29/03/16
 */
@Repository
public class BookRepoIml extends GenericRepo<Book> implements BookRepo {

  public BookRepoIml() {
    super(Book.class);
  }
}
