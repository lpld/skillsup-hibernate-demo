package ua.skillsup.javacourse.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
public class MagazineIssue extends Publication {

  @ManyToOne(optional = false)
  private Magazine magazine;
}
