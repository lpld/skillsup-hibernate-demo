package ua.skillsup.javacourse.bookstore.domain.store;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
public class BookStore {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Embedded
  private Address address;

  @ManyToMany(mappedBy = "bookStores")
  private List<Publication> publications;
}

