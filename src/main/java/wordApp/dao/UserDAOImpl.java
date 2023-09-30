package wordApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional
  public void save(User theUser) {
    entityManager.persist(theUser);
  }
}
