package ua.skillsup.javacourse.bookstore.domain.publication;

import org.springframework.stereotype.Repository;

import java.util.List;

import ua.skillsup.javacourse.bookstore.domain.book.BookPublication;
import ua.skillsup.javacourse.bookstore.domain.magazine.MagazineIssue;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface PublicationRepo {

  List<BookPublication> findBooksByName(String name);

  List<BookPublication> findBooksByGenreCheapestFirst(String genre, int limit);

  List<BookPublication> findBooksByGenrePopularFirst(String genre, int limit);

  List<MagazineIssue> findPopularMagazinesByGenre(String genre, int limit);
}
