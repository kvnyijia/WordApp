package wordApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import wordApp.entity.Table;
import wordApp.rest.table_class.GetTablesRes;
import wordApp.rest.table_class.TableNotFoundExp;
import wordApp.service.TableService;

@CrossOrigin(maxAge = 3600)
@RestController
public class TableController {

  private TableService service;

  @Autowired
  public TableController(TableService service) {
    this.service = service;
  }

  @PostMapping(value = "/tables", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public Boolean addTable(@RequestBody Table t) {
    service.save(t);
    return true;
  }

  @GetMapping("/tables")
  public GetTablesRes getTables(@RequestParam("owner") String owner) {
    return new GetTablesRes(
      owner,
      service.findAll_by_owner(owner)
    );
  }

  @GetMapping("/tables/{table_id}")
  public Table getTable(@PathVariable int table_id) {
    Table theTable = service.find(table_id);
    if (theTable == null) {
      throw new TableNotFoundExp(table_id);
    }
    return theTable;
  }

  @DeleteMapping("/tables/{table_id}")
  public boolean deleteTable(@PathVariable int table_id) {
    if (service.find(table_id) == null) {
      throw new TableNotFoundExp(table_id);
    }
    service.delete(table_id);
    return true;
  }
}
