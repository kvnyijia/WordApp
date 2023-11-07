package wordApp.rest.common_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorRes {
  
  private int status;
  private String message;
  private long timestamp;

  public ErrorRes() { }
  public ErrorRes(int status, String message, long timestamp) {
    this.status = status;
    this.message = message;
    this.timestamp = timestamp;
  }
  
  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public long getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public static ResponseEntity<ErrorRes> create_ResponseEntity_ErrorRes(HttpStatus status, Exception exp) {
    return new ResponseEntity<>(
      new ErrorRes(
        status.value(),
        exp.getMessage(),
        System.currentTimeMillis()
      ),
      status
    );
  }
}

