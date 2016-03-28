package ua.skillsup.javacourse.bookstore.domain.genre;

import ua.skillsup.javacourse.bookstore.domain.Repo;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface GenreRepo extends Repo<Genre> {

  Genre getGenre(String name);
}
