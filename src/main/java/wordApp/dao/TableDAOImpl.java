package wordApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import wordApp.entity.Table;

public class TableDAOImpl implements TableDAO {

  private EntityManager em;
  
  @Autowired
  public TableDAOImpl(EntityManager em) {
    this.em = em;
  }

  @Override
  public void save(Table theTable) {
    em.persist(theTable);
  }

  @Override
  public Table find(int table_id) {
    return em.find(Table.class, table_id);
  }

  @Override
  public List<Table> findAll_by_owner(String owner) {
    String qlString = "select * from Table where owner = " + owner;
    TypedQuery<Table> query = em.createQuery(qlString, Table.class);
    return query.getResultList();
  }

  @Override
  public Table update(Table theTable) {
    return em.merge(theTable);
  }

  @Override
  public void delete(int table_id) {
    Table theTable = em.find(Table.class, table_id);
    em.remove(theTable);
  }
}
