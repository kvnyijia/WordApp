package wordApp.rest.word_class;

import java.util.List;

import wordApp.entity.Word;

public class GetWordsRes {
  
  private int table_id;
  private List<Word> words;

  public GetWordsRes() { }
  public GetWordsRes(int table_id, List<Word> words) {
    this.table_id = table_id;
    this.words = words;
  }

  public int getTable_id() {
    return table_id;
  }
  public void setTable_id(int table_id) {
    this.table_id = table_id;
  }
  public List<Word> getWords() {
    return words;
  }
  public void setWords(List<Word> words) {
    this.words = words;
  }
}
