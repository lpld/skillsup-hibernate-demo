package ua.skillsup.javacourse.bookstore.persistence;

import org.hibernate.Session;

import java.util.List;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.domain.book.Book;
import ua.skillsup.javacourse.bookstore.domain.book.BookPublication;
import ua.skillsup.javacourse.bookstore.domain.genre.Genre;
import ua.skillsup.javacourse.bookstore.domain.genre.GenreRepo;
import ua.skillsup.javacourse.bookstore.domain.magazine.MagazineIssue;
import ua.skillsup.javacourse.bookstore.domain.publication.PublicationRepo;

import static java.util.stream.Collectors.toList;

/**
 * @author leopold
 * @since 13/03/16
 */
public class PublicationRepoImpl implements PublicationRepo {

  @Inject
  private Session session;

  @Inject
  private GenreRepo genreRepo;

  @Override
  public List<BookPublication> findBooksByName(String name) {

//    session.createQuery("FROM BookPublication p JOIN FETCH p.book WHERE p.book.titie LIKE :nm")
//        .setParameter("nm", "%" + name + "%")
//        .list();

    // todo: check this for n+1 problem
    final List<Book> books = castBooks(
        session
            .createQuery("FROM Book b where b.titie LIKE :nm")
            .setParameter("nm", "%" + name + "%")
            .list());

    return books.stream()
        .flatMap(b -> b.getPublications().stream())
        .collect(toList());

    // this is equal to:
    //
//    final List<BookPublication> publications = new ArrayList<>();
//    for (Book book : books) {
//      publications.addAll(book.getPublications());
//    }
//    return publications;
  }

  @Override
  public List<BookPublication> findBooksByGenreCheapestFirst(String genreName, int limit) {
    final Genre genre = genreRepo.getGenre(genreName);

    return castBookPublications(
        session
            .createQuery(
                "FROM BookPublication p " +
                "JOIN FETCH p.book " +
                "WHERE :g member of p.book.genres " +
                "ORDER BY p.price")
            .setParameter("g", genre)
            .setMaxResults(limit)
            .list()
    );
  }

  @Override
  public List<BookPublication> findBooksByGenrePopularFirst(String genreName, int limit) {
    final Genre genre = genreRepo.getGenre(genreName);

    return castBookPublications(
        session
            .createQuery(
                "FROM BookPublication p " +
                "JOIN FETCH p.book " +
                "WHERE :g member of p.book.genres " +
                "ORDER BY p.rating DESC")
            .setParameter("g", genre)
            .setMaxResults(limit)
            .list()
    );
  }

  @Override
  public List<MagazineIssue> findPopularMagazinesByGenre(String genreName, int limit) {
    final Genre genre = genreRepo.getGenre(genreName);

    return castMagazineIssues(
        session
            .createQuery(
                "SELECT DISTINCT p FROM MagazineIssue p " +
                "WHERE p.magazine.genre = :g " +
                "ORDER BY p.rating DESC")
            .setParameter("g", genre)
            .setMaxResults(limit)
            .list()
    );
  }

  @SuppressWarnings("unchecked")
  private static List<Book> castBooks(List list) {
    return list;
  }

  @SuppressWarnings("unchecked")
  private static List<BookPublication> castBookPublications(List list) {
    return list;
  }

  @SuppressWarnings("unchecked")
  private static List<MagazineIssue> castMagazineIssues(List list) {
    return list;
  }
}
