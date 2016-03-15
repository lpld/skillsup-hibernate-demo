package ua.skillsup.javacourse.bookstore.application;

import java.time.LocalDate;
import java.util.Set;

import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.Book;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface BookEditService {

  Author createAuthor(String name, String country, LocalDate birthday);

  Book createBook(Long authorId, String title, String summary, Set<String> genres);

  void quickAddBookAndAuthor(String title, String authorName);

}
