package wordApp.rest.user_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExpHandler {
  @ExceptionHandler
  public ResponseEntity<UserErrorRes> expHandler(UserNotFoundExp exc) {
    UserErrorRes err = new UserErrorRes();
    err.setStatus(HttpStatus.NOT_FOUND.value());
    err.setMsg(exc.getMessage());
    err.setTimeStamp(System.currentTimeMillis());
    return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<UserErrorRes> expHandler(UserUniqueViolationExp exc) {
    UserErrorRes err = new UserErrorRes();
    err.setStatus(HttpStatus.FORBIDDEN.value());
    err.setMsg(exc.getMessage());
    err.setTimeStamp(System.currentTimeMillis());
    return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler
  public ResponseEntity<UserErrorRes> expHandler(UserUnauthorizedExp exc) {
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    UserErrorRes err = new UserErrorRes();
    err.setStatus(status.value());
    err.setMsg(exc.getMessage());
    err.setTimeStamp(System.currentTimeMillis());
    return new ResponseEntity<>(err, status);
  }
}
