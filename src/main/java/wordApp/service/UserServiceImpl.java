package wordApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wordApp.dao.UserDAO;
import wordApp.entity.User;

@Service
public class UserServiceImpl implements UserService {
  private UserDAO dao;

  @Autowired
  public UserServiceImpl(UserDAO theDao) {
    this.dao = theDao;
  }

  @Transactional
  @Override
  public void save(User theUser) {
    dao.save(theUser);
  }

  @Override
  public User find(String username) {
    return dao.find(username);
  }

  @Transactional
  @Override
  public User update(User theUser) {
    return dao.update(theUser);
  }

  @Transactional
  @Override
  public void delete(String theUsername) {
    dao.delete(theUsername);
  }
}
