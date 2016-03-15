package ua.skillsup.javacourse.bookstore.domain.store;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.skillsup.javacourse.bookstore.domain.publication.PublicationAvailability;

/**
 * @author leopold
 * @since 8/03/16
 */
@Data
@EqualsAndHashCode(of = {"name", "address"})
@ToString(exclude = "publications")

@Entity
public class BookStore {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "bookStore")
  private Set<PublicationAvailability> publications;
}

