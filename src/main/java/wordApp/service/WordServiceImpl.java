package wordApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wordApp.dao.WordDAO;
import wordApp.entity.Word;

@Service
public class WordServiceImpl implements WordService {
  
  private WordDAO dao;

  @Autowired
  public WordServiceImpl(WordDAO dao) {
    this.dao = dao;
  }

  @Transactional
  @Override
  public void save(Word theWord) {
    dao.save(theWord);
  }

  @Override
  public Word find(int word_id) {
    return dao.find(word_id);
  }

  @Override
  public List<Word> findAll_by_table(int table_id) {
    return dao.findAll_by_table(table_id);
  }

  @Transactional
  @Override
  public Word update(Word theWord) {
    return dao.update(theWord);
  }

  @Transactional
  @Override
  public void delete(int word_id) {
    dao.delete(word_id);
  }
}
