package wordApp.dao;

import java.util.List;

import wordApp.entity.Table;
import wordApp.rest.common_class.RecordInt;

public interface TableDAO {
  void save(Table theTable);
  Table find(int table_id);
  List<Table> findAll_by_owner(String owner);
  Table update(Table theTable);
  void delete(int table_id);
  List<RecordInt> get_leaderboard();
}
