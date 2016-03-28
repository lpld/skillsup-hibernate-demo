package ua.skillsup.javacourse.bookstore.domain.book;

import java.util.List;

import ua.skillsup.javacourse.bookstore.domain.Repo;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface AuthorRepo extends Repo<Author> {

  List<Author> findByName(String name);
}
