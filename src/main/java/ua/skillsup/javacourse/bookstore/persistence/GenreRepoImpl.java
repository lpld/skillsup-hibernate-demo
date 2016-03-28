package ua.skillsup.javacourse.bookstore.persistence;

import org.springframework.stereotype.Repository;

import ua.skillsup.javacourse.bookstore.domain.genre.Genre;
import ua.skillsup.javacourse.bookstore.domain.genre.GenreRepo;

/**
 * @author leopold
 * @since 13/03/16
 */
@Repository
public class GenreRepoImpl extends GenericRepo<Genre> implements GenreRepo {

  public GenreRepoImpl() {
    super(Genre.class);
  }

  @Override
  public Genre getGenre(String name) {
    return findOneByField("name", name);
  }
}
