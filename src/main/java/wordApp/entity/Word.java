package wordApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Word {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="table_id")
  private int table_id;
  
  @Column(name="term")
  private String term;

  @Column(name="meaning")
  private String meaning;

  @Column(name="picture_url")
  private String picture_url;

  public Word() { }

  public Word(int table_id, String term, String meaning, String picture_url) {
    this.table_id = table_id;
    this.term = term;
    this.meaning = meaning;
    this.picture_url = picture_url;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  @Override
  public String toString() {
    return "Word [id=" + id + ", table_id=" + table_id + ", term=" + term + ", meaning=" + meaning + ", picture_url="
        + picture_url + "]";
  }
}
