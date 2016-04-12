package ua.skillsup.javacourse.bookstore.persistence;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import ua.skillsup.javacourse.bookstore.domain.security.User;
import ua.skillsup.javacourse.bookstore.domain.security.UserRepo;

/**
 * @author leopold
 * @since 12/04/16
 */
@Repository
public class UserRepoImpl extends GenericRepo<User> implements UserRepo {

  public UserRepoImpl() {
    super(User.class);
  }

  @Override
  public Optional<User> getByName(String username) {
    return findOneByField("username", username);
  }
}
