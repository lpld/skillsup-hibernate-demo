package ua.skillsup.javacourse.bookstore.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;

import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.AuthorRepo;

/**
 * @author leopold
 * @since 13/03/16
 */
@Repository
public class AuthorRepoImpl extends GenericRepo<Author> implements AuthorRepo {

  public AuthorRepoImpl() {
    super(Author.class);
  }

  @Override
  public List<Author> findByName(String name) {
    return findWhereFieldLike("name", name);
  }
}
