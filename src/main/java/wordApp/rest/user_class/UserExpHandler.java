package wordApp.rest.user_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import wordApp.rest.common_class.ErrorRes;

@ControllerAdvice
public class UserExpHandler {

  @ExceptionHandler
  public ResponseEntity<ErrorRes> expHandler(UserNotFoundExp exp) {
    return ErrorRes.create_ResponseEntity_ErrorRes(HttpStatus.NOT_FOUND, exp);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorRes> expHandler(UserUniqueViolationExp exp) {
    return ErrorRes.create_ResponseEntity_ErrorRes(HttpStatus.FORBIDDEN, exp);
  }

  @ExceptionHandler
  public ResponseEntity<ErrorRes> expHandler(UserUnauthorizedExp exp) {
    return ErrorRes.create_ResponseEntity_ErrorRes(HttpStatus.UNAUTHORIZED, exp);
  }
}
