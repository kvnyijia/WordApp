package wordApp.service;

import wordApp.entity.User;

public interface UserService {
  void save(User theUser);
  User find(String theUsername);
  User update(User theUser);
  void delete(String theUsername);
}
