package wordApp.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@jakarta.persistence.Table
public class Word implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @JsonIgnore 
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="table_id")
  private Table table;
  
  @Column(name="term")
  private String term;

  @Column(name="meaning")
  private String meaning;

  @Column(name="picture_url")
  private String picture_url;

  public Word() { }

  public Word(Table table, String term, String meaning, String picture_url) {
    this.table = table;
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

  public Table getTable() {
    return table;
  }

  public void setTable(Table table) {
    this.table = table;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }
}
