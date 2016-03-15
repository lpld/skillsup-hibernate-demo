package ua.skillsup.javacourse.bookstore.domain.publication;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.skillsup.javacourse.bookstore.domain.store.BookStore;

/**
 * @author leopold
 * @since 13/03/16
 */
@Data
@EqualsAndHashCode(of = {"publication", "bookStore"})
@ToString

@Entity
public class PublicationAvailability {

  @Id
  private Long id;

  @ManyToOne
  private Publication publication;

  @ManyToOne
  private BookStore bookStore;

  private Boolean available;

  private Double price;
}
