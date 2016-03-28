package ua.skillsup.javacourse.bookstore.persistence;

import java.util.List;

/**
 * @author leopold
 * @since 13/03/16
 */
final class Util {

  private Util() {
  }

  @SuppressWarnings("unchecked")
  static <T> List<T> castList(List list) {
    return list;
  }

  @SuppressWarnings("unchecked")
  static <T> T cast(Object o) {
    return (T) o;
  }
}
