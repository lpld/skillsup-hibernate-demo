package ua.skillsup.javacourse.bookstore.domain.publication;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author leopold
 * @since 8/03/16
 */
@Data
@EqualsAndHashCode(of = "name")
@ToString(exclude = "publications")

@Entity
public class Publisher {

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany(mappedBy = "publisher")
  private List<Publication> publications;

}
