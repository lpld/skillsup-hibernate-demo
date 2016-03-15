package ua.skillsup.javacourse.bookstore.domain.book;

import java.util.List;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface AuthorRepo {

  Author getById(Long id);

  List<Author> findByName(String name);

  void add(Author author);

}
