package wordApp.rest.word_class;

public class CreateWordReq {
  
  private int table_id;
  private String term;
  private String meaning;
  private String picture_url;

  public CreateWordReq() {}
  public CreateWordReq(int table_id, String term, String meaning, String picture_url) {
    this.table_id = table_id;
    this.term = term;
    this.meaning = meaning;
    this.picture_url = picture_url;
  }
  public int getTable_id() {
    return table_id;
  }
  public void setTable_id(int table_id) {
    this.table_id = table_id;
  }
  public String getTerm() {
    return term;
  }
  public void setTerm(String term) {
    this.term = term;
  }
  public String getMeaning() {
    return meaning;
  }
  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }
  public String getPicture_url() {
    return picture_url;
  }
  public void setPicture_url(String picture_url) {
    this.picture_url = picture_url;
  }  
}
