package ua.skillsup.javacourse.bookstore.domain.genre;

import org.springframework.stereotype.Repository;

/**
 * @author leopold
 * @since 13/03/16
 */
@Repository
public interface GenreRepo {

  Genre getGenre(String name);

  Genre add(String name);
}
