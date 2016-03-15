package ua.skillsup.javacourse.bookstore.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.domain.book.Author;
import ua.skillsup.javacourse.bookstore.domain.book.AuthorRepo;

import static ua.skillsup.javacourse.bookstore.persistence.Util.castList;

/**
 * @author leopold
 * @since 13/03/16
 */
@Repository
public class AuthorRepoImpl implements AuthorRepo {

  @Inject
  private SessionFactory sessionFactory;

  @Override
  public Author getById(Long id) {
    return sessionFactory.getCurrentSession()
        .get(Author.class, id);
  }

  @Override
  public List<Author> findByName(String name) {
    return castList(
        sessionFactory.getCurrentSession()
            .createCriteria(Author.class)
            .add(Restrictions.ilike("name", "%" + name + "%"))
            .list()
    );
  }

  @Override
  public void add(Author author) {
    sessionFactory.getCurrentSession().save(author);
  }
}
