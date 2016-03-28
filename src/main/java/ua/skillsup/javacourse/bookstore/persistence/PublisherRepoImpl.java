package ua.skillsup.javacourse.bookstore.persistence;

import org.springframework.stereotype.Repository;

import ua.skillsup.javacourse.bookstore.domain.publication.Publisher;
import ua.skillsup.javacourse.bookstore.domain.publication.PublisherRepo;

/**
 * @author leopold
 * @since 29/03/16
 */
@Repository
public class PublisherRepoImpl extends GenericRepo<Publisher> implements PublisherRepo {

  protected PublisherRepoImpl() {
    super(Publisher.class);
  }

  @Override
  public Publisher findByName(String name) {
    return findOneByField("name", name);
  }
}
