package ua.skillsup.javacourse.bookstore.domain;

import java.util.List;

/**
 * @author leopold
 * @since 29/03/16
 */
public interface Repo<T> {

  T getById(Long id);

  T findOneByField(String field, String value);

  List<T> findWhereFieldLike(String field, String value);

  void add(T t);

  List<T> getAll();
}
