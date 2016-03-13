package ua.skillsup.javacourse.bookstore.persistence;

import org.hibernate.Session;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.domain.genre.Genre;
import ua.skillsup.javacourse.bookstore.domain.genre.GenreRepo;

/**
 * @author leopold
 * @since 13/03/16
 */
public class GenreRepoImpl implements GenreRepo {

  @Inject
  private Session session;

  @Override
  public Genre getGenre(String name) {
    return (Genre) session.createQuery("FROM Genre g WHERE g.name = :name")
        .setParameter("name", name)
        .uniqueResult();
  }

  @Override
  public Genre add(String name) {
    final Genre genre = new Genre(name);
    session.persist(genre);
    return genre;
  }
}
