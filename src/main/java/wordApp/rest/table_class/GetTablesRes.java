package wordApp.rest.table_class;

import java.util.List;

import wordApp.entity.Table;

public class GetTablesRes {
  
  private String owner;
  private List<Table> tables;
  
  public GetTablesRes() { }
  
  public GetTablesRes(String owner, List<Table> tables) {
    this.owner = owner;
    this.tables = tables;
  }

  public String getOwner() {
    return owner;
  }
  public List<Table> getTables() {
    return tables;
  }
  public void setOwner(String owner) {
    this.owner = owner;
  }
  public void setTables(List<Table> tables) {
    this.tables = tables;
  }
}
