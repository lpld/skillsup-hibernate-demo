package ua.skillsup.javacourse.bookstore.domain.publication;

import ua.skillsup.javacourse.bookstore.domain.Repo;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface PublisherRepo extends Repo<Publisher> {

  Publisher findByName(String name);
}
