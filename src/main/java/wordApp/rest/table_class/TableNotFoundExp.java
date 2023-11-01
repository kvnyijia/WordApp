package wordApp.rest.table_class;

public class TableNotFoundExp extends RuntimeException {

  public TableNotFoundExp(int arg0) {
    super("Table not found: " + arg0);
  }
}
