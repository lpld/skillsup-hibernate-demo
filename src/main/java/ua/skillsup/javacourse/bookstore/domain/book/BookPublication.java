package ua.skillsup.javacourse.bookstore.domain.book;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
public class BookPublication extends Publication {

  @ManyToOne(optional = false)
  private Book book;

  // ---------

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
