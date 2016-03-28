package ua.skillsup.javacourse.bookstore.domain.publication;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.skillsup.javacourse.bookstore.domain.store.BookStore;

/**
 * @author leopold
 * @since 8/03/16
 */
@Data
@EqualsAndHashCode(of = {"publisher", "date"})
@ToString(of = {"id", "date", "publisher"})

@Entity
public abstract class Publication {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private String title;

  private String summary;

  private Double price;

  private Double rating;

  @OneToMany(mappedBy = "publication")
  private Set<PublicationAvailability> bookStores;

  @ManyToOne(optional = false)
  private Publisher publisher;

}
