package ua.skillsup.javacourse.bookstore.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author leopold
 * @since 8/03/16
 */
@Entity
@Table(name = "Book")
public class Book {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String titie;

  @Column(name = "abstract")
  private String summary;

  @Column(nullable = false)
  private LocalDate firstPublished;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "author_id", nullable = false)
  private Author author;

  @ManyToMany(fetch = FetchType.LAZY)
  private List<Genre> genres;

  @OneToMany(mappedBy = "book")
  private List<BookPublication> publications;

  // ------------------------------------------------------------------------------------------ //

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitie() {
    return titie;
  }

  public void setTitie(String titie) {
    this.titie = titie;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public LocalDate getFirstPublished() {
    return firstPublished;
  }

  public void setFirstPublished(LocalDate firstPublished) {
    this.firstPublished = firstPublished;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public List<BookPublication> getPublications() {
    return publications;
  }

  public void setPublications(
      List<BookPublication> publications) {
    this.publications = publications;
  }

  @Override
  public String toString() {
    return "Book{" +
           "id=" + id +
           ", titie='" + titie + '\'' +
           ", summary='" + summary + '\'' +
           ", firstPublished=" + firstPublished +
           ", author=" + author +
           ", genres=" + genres +
           '}';
  }


}
