package ua.skillsup.javacourse.bookstore.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author leopold
 * @since 29/03/16
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

  public EntityNotFoundException(String message) {
    super(message);
  }
}
