package ua.skillsup.javacourse.bookstore.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.application.BookEditService;
import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.AuthorRepo;
import ua.skillsup.javacourse.bookstore.domain.book.Book;
import ua.skillsup.javacourse.bookstore.domain.genre.Genre;
import ua.skillsup.javacourse.bookstore.domain.genre.GenreRepo;

/**
 * @author leopold
 * @since 13/03/16
 */
@Service
@Transactional
public class BookEditServiceImpl implements BookEditService {

  @Inject
  private AuthorRepo authorRepo;

  @Inject
  private GenreRepo genreRepo;

  @Override
  @Transactional
  public Author createAuthor(String name, String country, LocalDate birthday) {
    final Author author = new Author();
    author.setName(name);
    author.setBirthday(birthday);
    author.setCountry(country);

    authorRepo.add(author);
    return author;
  }

  public Book createBook(Long authorId, String title, String summary, Set<String> genres) {

    final Author author = authorRepo.getById(authorId);

    final Book book = new Book();
    book.setTitle(title);
    book.setSummary(summary);
    author.addBook(book);

    final Set<Genre> bookGenres = genres.stream().map(g -> {
      Genre genre = genreRepo.getGenre(g);
      if (genre == null) {
        genre = genreRepo.add(g);
      }
      return genre;
    }).collect(Collectors.toSet());

    book.setGenres(bookGenres);

    return book;
  }

  @Override
  public void quickAddBookAndAuthor(String title, String authorName) {

  }
}
