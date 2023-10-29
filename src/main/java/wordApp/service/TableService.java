package wordApp.service;

import java.util.List;

import wordApp.entity.Table;

public interface TableService {
  void save(Table theTable);
  Table find(int table_id);
  List<Table> findAll_by_owner(String owner);
  Table update(Table theTable);
  void delete(int table_id);
}
