package ua.skillsup.javacourse.bookstore.domain.publication;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ua.skillsup.javacourse.bookstore.domain.store.BookStore;

/**
 * @author leopold
 * @since 8/03/16
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Publication {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private LocalDate date;

  private String isbn;

  @Column(nullable = false)
  private String name;

  private String summary;

  private Double price;

  private Double rating;

  @ManyToMany
  @JoinTable(
      name = "Publication_BookStore",
      joinColumns = {@JoinColumn(name = "bookStores_id")},
      inverseJoinColumns = {@JoinColumn(name = "publications_id")}
  )
  private List<BookStore> bookStores;

  @ManyToOne(optional = false)
  private Publisher publisher;

  // ----


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public List<BookStore> getBookStores() {
    return bookStores;
  }

  public void setBookStores(
      List<BookStore> bookStores) {
    this.bookStores = bookStores;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }
}
