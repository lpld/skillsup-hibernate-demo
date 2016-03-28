package ua.skillsup.javacourse.bookstore.domain.book;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 8/03/16
 */
@Data
@EqualsAndHashCode(of = {"book", "isbn"}, callSuper = true)
@ToString(of = {"book", "isbn"}, callSuper = true)

@Entity
public class BookPublication extends Publication {

  private String isbn;

  @ManyToOne
  private Book book;
}
