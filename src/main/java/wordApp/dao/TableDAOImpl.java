package wordApp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import wordApp.entity.Table;
import wordApp.rest.common_class.RecordInt;

@Repository
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
    String qlString = "from Table where owner = ?1";
    TypedQuery<Table> query = em.createQuery(qlString, Table.class);
    return query.setParameter(1, owner)
      .getResultList();
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

  @Override
  public List<RecordInt> get_leaderboard() {
    String qlString = "select t.owner, count(*) as cnt from Table t group by owner order by cnt desc limit 5";
    TypedQuery<Object[]> query = em.createQuery(qlString, Object[].class);
    List<RecordInt> res = new ArrayList<>();
    for (Object[] x: query.getResultList()) {
      res.add(new RecordInt((String) x[0], (int)(long) x[1]));
    }
    return res;
  }
}
