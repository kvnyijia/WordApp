package wordApp.rest.table_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TableExpHandler {
  
  @ExceptionHandler
  public ResponseEntity<TableErrorRes> exphandler(TableNotFoundExp exp) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    TableErrorRes res = new TableErrorRes(
      status.value(),
      exp.getMessage(),
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(res, status);
  }
}
