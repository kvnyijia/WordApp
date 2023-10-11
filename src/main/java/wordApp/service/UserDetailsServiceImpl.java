package wordApp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import wordApp.dao.UserDAO;
import wordApp.entity.User;

@Service
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
