package wordApp.dao;

import wordApp.entity.User;

public interface UserDAO {
  void save(User theUser);

  User find(String theUsername);

  // List<User> findAll();

  User update(User theUser);

  void delete(String theUsername);
}
