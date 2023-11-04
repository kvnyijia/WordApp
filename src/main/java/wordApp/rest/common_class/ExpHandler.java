package wordApp.rest.common_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExpHandler {
  
  @ExceptionHandler
  public ResponseEntity<ErrorRes> expHandler(MissingServletRequestParameterException exp) {
    return respond_bad_request(exp);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorRes> expHandler(MethodArgumentTypeMismatchException exp) {
    return respond_bad_request(exp);
  }

  private ResponseEntity<ErrorRes> respond_bad_request(Exception exp) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ErrorRes res = new ErrorRes(
      status.value(), 
      exp.getMessage(), 
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(res, status);
  }
}
