package ua.skillsup.javacourse.bookstore.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.domain.genre.Genre;
import ua.skillsup.javacourse.bookstore.domain.genre.GenreRepo;

import static ua.skillsup.javacourse.bookstore.persistence.Util.castList;

/**
 * @author leopold
 * @since 13/03/16
 */
@Repository
public class GenreRepoImpl implements GenreRepo {

  @Inject
  private SessionFactory sessionFactory;

  @Override
  public List<Genre> getAllGenres() {
    return castList(
        sessionFactory.getCurrentSession()
            .createQuery("FROM Genre g")
            .list());
  }

  public Genre getGenre(String name) {
    return Util.<Genre>castList(
        sessionFactory.getCurrentSession()
            .createQuery("FROM Genre g WHERE g.name = :name")
            .setParameter("name", name)
            .list())
        .stream()
        .findAny()
        .orElse(null);
  }

  @Override
  public Genre add(String name) {
    final Genre genre = new Genre(name);
    sessionFactory.getCurrentSession().persist(genre);
    return genre;
  }
}
