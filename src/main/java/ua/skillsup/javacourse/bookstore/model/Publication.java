package ua.skillsup.javacourse.bookstore.model;

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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

  @ManyToMany
  @JoinTable(
      name = "Publication_BookStore",
      joinColumns = {@JoinColumn(name = "bookStores_id")},
      inverseJoinColumns = {@JoinColumn(name = "publications_id")}
  )
  private List<BookStore> bookStores;

  @ManyToOne(optional = false)
  private Publisher publisher;
}
