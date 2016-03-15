package ua.skillsup.javacourse.bookstore.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.application.BookSearchService;
import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.AuthorRepo;
import ua.skillsup.javacourse.bookstore.domain.publication.Publication;
import ua.skillsup.javacourse.bookstore.domain.publication.PublicationRepo;
import ua.skillsup.javacourse.bookstore.domain.book.BookPublication;
import ua.skillsup.javacourse.bookstore.domain.magazine.MagazineIssue;

import static java.util.stream.Collectors.toList;

/**
 * @author leopold
 * @since 13/03/16
 */
@Service
@Transactional
public class BookSearchServiceImpl implements BookSearchService {

  @Inject
  private PublicationRepo publicationRepo;

  @Inject
  private AuthorRepo authorRepo;

  @Override
  public List<Author> findAuthor(String name) {
    return authorRepo.findByName(name);
  }

  // this method simply delegates the call to repository
  @Override
  public List<BookPublication> findPublicationsForBook(String name) {
    return publicationRepo.findBooksByName(name);
  }

  // here we have some application related logic
  @Override
  public List<Publication> findBestForGenre(String genre) {
    final List<BookPublication> cheapest =
        publicationRepo.findBooksByGenreCheapestFirst(genre, 5);

    final List<BookPublication> popular =
        publicationRepo.findBooksByGenrePopularFirst(genre, 5);

    final List<MagazineIssue> magazines =
        publicationRepo.findPopularMagazinesByGenre(genre, 5);

    // join three lists
    return Stream.of(cheapest, popular, magazines)
        .flatMap(List::stream)
        .collect(toList());

  }
}
