package ua.skillsup.javacourse.bookstore.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.domain.Repo;

/**
 * @author leopold
 * @since 29/03/16
 */
public abstract class GenericRepo<T> implements Repo<T> {

  private final Class<T> clazz;

  @Inject
  protected SessionFactory sessionFactory;

  protected GenericRepo(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Override
  public T getById(Long id) {
    return sessionFactory.getCurrentSession().get(clazz, id);
  }

  @Override
  public List<T> getAll() {
    return Util.castList(
        sessionFactory.getCurrentSession()
            .createCriteria(clazz)
            .list()
    );
  }

  @Override
  public T findOneByField(String field, String value) {
    return Util.cast(
        sessionFactory.getCurrentSession()
            .createCriteria(clazz)
            .add(Restrictions.eq(field, value))
            .uniqueResult()
    );
  }

  @Override
  public List<T> findWhereFieldLike(String field, String value) {
    return Util.castList(
        sessionFactory.getCurrentSession()
            .createCriteria(clazz)
            .add(Restrictions.ilike(field, "%" + value + "%"))
            .list()
    );
  }

  @Override
  public void add(T t) {
    sessionFactory.getCurrentSession().save(t);
  }
}
