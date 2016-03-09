package ua.skillsup.javacourse.bookstore.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
@Table(name = "Author")
public class Author {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue
  private Long id;

  @Basic(fetch = FetchType.EAGER, optional = false)
  @Column(name = "name", nullable = false)
  private String name;

  @Basic(fetch = FetchType.EAGER, optional = false)
  @Column(name = "country", nullable = false)
  private String country;

  @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Book> books;

  @Version
  private Integer version;

  // ------------------------------------------------------------------------------------------ //

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "Author{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", country='" + country + '\'' +
           '}';
  }
}
