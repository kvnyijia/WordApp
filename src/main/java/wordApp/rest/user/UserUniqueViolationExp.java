package wordApp.rest.user;

public class UserUniqueViolationExp extends RuntimeException {

  public UserUniqueViolationExp(String arg0) {
    super("This username already exists: " + arg0);
  }

  public UserUniqueViolationExp(Throwable arg0) {
    super(arg0);
  }

  public UserUniqueViolationExp(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }
  
}
