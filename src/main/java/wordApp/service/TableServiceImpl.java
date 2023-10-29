package wordApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import wordApp.dao.TableDAO;
import wordApp.entity.Table;

public class TableServiceImpl implements TableService {
  
  private TableDAO dao;

  @Autowired
  public TableServiceImpl(TableDAO dao) {
    this.dao = dao;
  }

  @Override
  public void save(Table theTable) {
    dao.save(theTable);
  }

  @Override
  public Table find(int table_id) {
    return dao.find(table_id);
  }

  @Override
  public List<Table> findAll_by_owner(String owner) {
    return dao.findAll_by_owner(owner);
  }

  @Override
  public Table update(Table theTable) {
    return dao.update(theTable);
  }

  @Override
  public void delete(int table_id) {
    dao.delete(table_id);
  }
}
