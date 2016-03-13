package ua.skillsup.javacourse.bookstore.domain.magazine;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ua.skillsup.javacourse.bookstore.domain.genre.Genre;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
public class Magazine {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String title;

  private String summary;

  @ManyToOne
  private Genre genre;

  @OneToMany(mappedBy = "magazine")
  private List<MagazineIssue> issues;
}
