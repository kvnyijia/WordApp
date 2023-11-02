package wordApp.rest.user_class;

public class LoginUserRes {
  private String access_token;
  private GetUserRes user;
  public LoginUserRes() { }
  public LoginUserRes(String access_token, GetUserRes user) {
    this.access_token = access_token;
    this.user = user;
  }
  public String getAccess_token() {
    return access_token;
  }
  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }
  public GetUserRes getUser() {
    return user;
  }
  public void setUser(GetUserRes user) {
    this.user = user;
  }
}
