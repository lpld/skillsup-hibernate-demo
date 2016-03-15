package ua.skillsup.javacourse.bookstore.application;

import java.util.List;

import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.BookPublication;
import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface BookSearchService {

  List<Author> findAuthor(String name);

  List<BookPublication> findPublicationsForBook(String name);

  List<Publication> findBestForGenre(String genre);
}
