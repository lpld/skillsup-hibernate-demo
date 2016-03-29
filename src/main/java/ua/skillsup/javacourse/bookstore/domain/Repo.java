package ua.skillsup.javacourse.bookstore.domain;

import java.util.List;
import java.util.Optional;

/**
 * @author leopold
 * @since 29/03/16
 */
public interface Repo<T> {

  Optional<T> getById(Long id);

  T findOneByField(String field, String value);

  List<T> findWhereFieldLike(String field, String value);

  void add(T t);

  List<T> getAll();
}
