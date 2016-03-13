package ua.skillsup.javacourse.bookstore.domain.publication;

/**
 * @author leopold
 * @since 13/03/16
 */
public interface PublisherRepo {

  Publisher findByName(String name);

}
