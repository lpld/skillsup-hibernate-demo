package ua.skillsup.javacourse.bookstore.demo;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.application.BookEditService;

/**
 * @author leopold
 * @since 13/03/16
 */
public class FillDbUseCase {

  @Inject
  BookEditService bookEditService;

  public void fillDatabase() {

  }
}
