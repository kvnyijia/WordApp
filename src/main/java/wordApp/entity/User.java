package wordApp.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user_table")
public class User {
  @Id
  @Column(name="username")
  private String username;

  @Column(name="email")
  private String email;

  @Column(name="hashed_password")
  private String hashed_password;

  @ManyToMany
  private Set<Role> roles;

  private boolean enabled;

  public User() {}

  public User(String username, String email, String hashed_password) {
    this.username = username;
    this.email = email;
    this.hashed_password = hashed_password;
  }

  public User(String username, String email, String hashed_password, Set<Role> roles, boolean enabled) {
    this.username = username;
    this.email = email;
    this.hashed_password = hashed_password;
    this.roles = roles;
    this.enabled = enabled;
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

  public String getHashed_password() {
    return hashed_password;
  }

  public void setHashed_password(String hashed_password) {
    this.hashed_password = hashed_password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public String toString() {
    return "User [username=" + username + ", email=" + email + ", hashed_password=" + hashed_password + ", roles="
        + roles + ", enabled=" + enabled + "]";
  } 
}
