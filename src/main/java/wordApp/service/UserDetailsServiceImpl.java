package wordApp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import wordApp.dao.UserDAO;
import wordApp.entity.User;

public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserDAO dao;

  public UserDetailsServiceImpl(UserDAO dao) {
    this.dao = dao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = dao.find(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new UserDetailsImpl(user);
  }
}
