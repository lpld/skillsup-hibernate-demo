package ua.skillsup.javacourse.bookstore.application.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.domain.security.UserRepo;

import static java.util.stream.Collectors.toSet;

/**
 * @author leopold
 * @since 12/04/16
 */
@Service
public class UserService implements UserDetailsService {

  @Inject
  private UserRepo userRepo;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepo
        .getByName(username)
        .map(u ->
                 new User(
                     u.getUsername(),
                     u.getPassword(),
                     u.isEnabled(),
                     true, true, true,
                     u.getRoles().stream()
                         .map(r -> new SimpleGrantedAuthority(r.name()))
                         .collect(toSet())
                 )
        )
        .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
  }
}
