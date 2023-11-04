package wordApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import wordApp.entity.Word;

@Repository
public class WordDAOImpl implements WordDAO {

  private EntityManager em;

  @Autowired
  public WordDAOImpl(EntityManager em) {
    this.em = em;
  }

  @Override
  public void save(Word theWord) {
    em.persist(theWord);
  }

  @Override
  public Word find(int word_id) {
    return em.find(Word.class, word_id);
  }

  @Override
  public List<Word> findAll_by_table(int table_id) {
    String qlString = "from Word where table_id = ?1";
    TypedQuery<Word> query = em.createQuery(qlString, Word.class);
    return query.setParameter(1, table_id)
      .getResultList();
  }

  @Override
  public Word update(Word theWord) {
    return em.merge(theWord);
  }
  
  @Override
  public void delete(int word_id) {
    Word theWord = em.find(Word.class, word_id);
    em.remove(theWord);
  }
}
