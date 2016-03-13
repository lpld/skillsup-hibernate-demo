package ua.skillsup.javacourse.bookstore.domain.store;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author leopold
 * @since 8/03/16
 */
@Embeddable
public class Address {

  @Column(nullable = false)
  private String country;

  @Column(nullable = false)
  private String city;

  private String zip;
  private String street1;
  private String street2;

  // -----------------------
}
