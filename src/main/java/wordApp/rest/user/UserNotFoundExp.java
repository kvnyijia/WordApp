package wordApp.rest.user;

public class UserNotFoundExp extends RuntimeException {

  public UserNotFoundExp(String arg0) {
    super("User not found: " + arg0);
  }

  public UserNotFoundExp(Throwable arg0) {
    super(arg0);
  }

  public UserNotFoundExp(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }
  
}
