package ua.skillsup.javacourse.bookstore.domain.magazine;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
public class MagazineIssue extends Publication {

  @ManyToOne(optional = false)
  private Magazine magazine;
}
