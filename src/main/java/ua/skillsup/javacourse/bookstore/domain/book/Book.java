package ua.skillsup.javacourse.bookstore.domain.book;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@EqualsAndHashCode(of = {"title", "author"})
@ToString(exclude = {"summary", "genres", "publications"})

@Entity
public class Book {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(name = "abstract")
  private String summary;

  @Column
  private LocalDate firstPublished;

  @ManyToOne(optional = false)
  private Author author;

  @ManyToMany
  private Set<Genre> genres;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  private Set<BookPublication> publications;

  public BookPublication addPublication(Publisher publisher, String isbn, LocalDate date) {
    final BookPublication publication = new BookPublication();
    publication.setPublisher(publisher);
    publication.setBook(this);
    publication.setDate(date);
    publication.setTitle(this.title);
    publication.setIsbn(isbn);

    if (publications == null) {
      publications = new HashSet<>();
    }
    publications.add(publication);

    return publication;
  }
}
