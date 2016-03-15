package ua.skillsup.javacourse.bookstore.domain.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author leopold
 * @since 8/03/16
 */
@Data
@EqualsAndHashCode(of = {"name", "birthday"})
@ToString(exclude = "books")

@Entity
public class Author {

  @Id
  @GeneratedValue
  private Long id;

  private LocalDate birthday;

  @Column(nullable = false)
  private String name;

  private String country;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<Book> books;

  @Version
  private Integer version;

  public void addBook(Book book) {
    book.setAuthor(this);
    if (books == null) {
      books = new ArrayList<>();
    }
    this.books.add(book);
  }
}
