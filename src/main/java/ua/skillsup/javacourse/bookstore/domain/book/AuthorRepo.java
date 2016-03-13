package ua.skillsup.javacourse.bookstore.domain.book;

import org.springframework.stereotype.Repository;

/**
 * @author leopold
 * @since 13/03/16
 */
@Repository
public interface AuthorRepo {

  Author getById(Long id);

  Author findByName(String name);

  void add(Author author);

}
