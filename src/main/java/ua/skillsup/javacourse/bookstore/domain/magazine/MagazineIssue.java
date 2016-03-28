package ua.skillsup.javacourse.bookstore.domain.magazine;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 8/03/16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
public class MagazineIssue extends Publication {

  @ManyToOne
  private Magazine magazine;
}
