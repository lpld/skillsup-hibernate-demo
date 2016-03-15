package ua.skillsup.javacourse.bookstore.domain.magazine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.skillsup.javacourse.bookstore.domain.genre.Genre;
import ua.skillsup.javacourse.bookstore.domain.publication.Publisher;

/**
 * @author leopold
 * @since 8/03/16
 */
@Data
@EqualsAndHashCode(of = "title")
@ToString(exclude = {"issues", "summary"})

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

  @ManyToOne(optional = false)
  private Publisher publisher;

  public void addIssue(LocalDate date) {

    final MagazineIssue issue = new MagazineIssue();
    issue.setMagazine(this);
    issue.setDate(date);
    issue.setTitle(this.getTitle() + " " + date.format(DateTimeFormatter.ISO_DATE));
    issue.setPublisher(this.publisher);

    issues.add(issue);

  }
}
