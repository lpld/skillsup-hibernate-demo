package ua.skillsup.javacourse.bookstore.domain.publication;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
public class Publisher {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "publisher")
  private List<Publication> publications;

}
