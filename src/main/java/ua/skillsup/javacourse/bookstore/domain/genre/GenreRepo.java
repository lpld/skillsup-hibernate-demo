package ua.skillsup.javacourse.bookstore.domain.genre;

import java.util.List;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface GenreRepo {

  List<Genre> getAllGenres();

  Genre getGenre(String name);

  Genre add(String name);
}
