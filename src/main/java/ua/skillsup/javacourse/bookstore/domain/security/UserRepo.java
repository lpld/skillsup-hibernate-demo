package ua.skillsup.javacourse.bookstore.domain.security;

import java.util.Optional;

import ua.skillsup.javacourse.bookstore.domain.Repo;

/**
 * @author leopold
 * @since 12/04/16
 */
public interface UserRepo extends Repo<User> {

  Optional<User> getByName(String username);

}
