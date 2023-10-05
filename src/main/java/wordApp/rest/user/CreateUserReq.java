package wordApp.rest.user;

public class CreateUserReq {
  private String username;
  private String email;
  private String password;
  public CreateUserReq(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
  public CreateUserReq() {
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}