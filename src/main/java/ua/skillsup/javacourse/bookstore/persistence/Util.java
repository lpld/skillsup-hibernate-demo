package ua.skillsup.javacourse.bookstore.persistence;

import java.util.List;

/**
 * @author leopold
 * @since 13/03/16
 */
public final class Util {

  private Util() {
  }

  @SuppressWarnings("unchecked")
  public static <T> List<T> castList(List list) {
    return list;
  }
}
