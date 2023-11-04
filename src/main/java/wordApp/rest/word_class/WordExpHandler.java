package wordApp.rest.word_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import wordApp.rest.common_class.ErrorRes;

@ControllerAdvice
public class WordExpHandler {
  
  @ExceptionHandler
  public ResponseEntity<ErrorRes> exphandler(WordNotFoundExp exp) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorRes res = new ErrorRes(
      status.value(), 
      exp.getMessage(), 
      System.currentTimeMillis()
    );
    return new ResponseEntity<>(res, status);
  }
}
