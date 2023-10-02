package wordApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import wordApp.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
  private EntityManager entityManager;

  @Autowired
  public UserDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void save(User theUser) {
    entityManager.persist(theUser);
  }

  @Override
  public User find(String theUsername) {
    return entityManager.find(User.class, theUsername);
  }

  @Override
  public User update(User theUser) {
    User updatedUser = entityManager.merge(theUser);
    return updatedUser;
  }

  @Override
  public void delete(String theUsername) {
    User theUser = entityManager.find(User.class, theUsername);
    entityManager.remove(theUser);
  }
  
}
