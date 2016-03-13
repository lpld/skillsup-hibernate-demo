package ua.skillsup.javacourse.bookstore.application;

import org.springframework.stereotype.Service;

import java.util.List;

import ua.skillsup.javacourse.bookstore.domain.publication.Publication;
import ua.skillsup.javacourse.bookstore.domain.book.BookPublication;

/**
 * @author leopold
 * @since 13/03/16
 */
@Service
public interface BookSearchService {

  List<BookPublication> findPublicationsForBook(String name);

  List<Publication> findBestForGenre(String genre);
}
