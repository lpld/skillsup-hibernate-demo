package ua.skillsup.javacourse.bookstore.model;

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
public class Magazine {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String title;

  private String summary;

  @OneToMany(mappedBy = "magazine")
  private List<MagazineIssue> issues;

}
