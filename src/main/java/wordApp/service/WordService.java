package wordApp.service;

import java.util.List;

import wordApp.entity.Word;

public interface WordService {
  void save(Word theWord);
  Word find(int word_id);
  List<Word> findAll_by_table(int table_id);
  Word update(Word theWord);
  void delete(int word_id);
}
