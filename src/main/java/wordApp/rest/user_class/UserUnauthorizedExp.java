package wordApp.rest.user_class;

public class UserUnauthorizedExp extends RuntimeException {

  public UserUnauthorizedExp(String arg0) {
    super(arg0);
  }

  public UserUnauthorizedExp(Throwable arg0) {
    super(arg0);
  }

  public UserUnauthorizedExp(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }
  
}
