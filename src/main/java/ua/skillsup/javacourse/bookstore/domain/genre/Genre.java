package ua.skillsup.javacourse.bookstore.domain.genre;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author leopold
 * @since 8/03/16
 */
@Data
@EqualsAndHashCode(of = "name")
@ToString

@Entity
public class Genre {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  public Genre() {
  }

  public Genre(String name) {
    this.name = name;
  }
}
