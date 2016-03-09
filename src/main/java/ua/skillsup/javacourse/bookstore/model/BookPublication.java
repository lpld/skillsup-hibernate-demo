package ua.skillsup.javacourse.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
public class BookPublication extends Publication {

  @ManyToOne(optional = false)
  private Book book;

}
