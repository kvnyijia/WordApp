package wordApp.rest.table_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import wordApp.rest.common_class.ErrorRes;

@ControllerAdvice
public class TableExpHandler {
  
  @ExceptionHandler
  public ResponseEntity<ErrorRes> exphandler(TableNotFoundExp exp) {
    return ErrorRes.create_ResponseEntity_ErrorRes(HttpStatus.NOT_FOUND, exp);
  }
}
