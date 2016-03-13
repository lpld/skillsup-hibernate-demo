package ua.skillsup.javacourse.bookstore.persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.AuthorRepo;

/**
 * @author leopold
 * @since 13/03/16
 */
public class AuthorRepoImpl implements AuthorRepo {

  @Inject
  private Session session;

  @Override
  public Author getById(Long id) {
    return session.get(Author.class, id);
  }

  @Override
  public Author findByName(String name) {
    return (Author) session
        .createCriteria(Author.class)
        .add(Restrictions.ilike("name", name))
        .uniqueResult();
  }

  @Override
  public void add(Author author) {
    session.persist(author);
  }
}
