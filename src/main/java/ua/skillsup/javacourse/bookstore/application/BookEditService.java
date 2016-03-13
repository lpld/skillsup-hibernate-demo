package ua.skillsup.javacourse.bookstore.application;

import org.springframework.stereotype.Service;

/**
 * @author leopold
 * @since 13/03/16
 */
@Service
public interface BookEditService {

  void addAuthor(String name, String country);

  void quickAddBookAndAuthor(String title, String authorName);

}
