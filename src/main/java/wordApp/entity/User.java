package wordApp.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user_table")
public class User implements Serializable {
  @Id
  @Column(name="username")
  private String username;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @ManyToMany
  private Set<Role> roles;

  private boolean enabled;

  public User() {}

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public User(String username, String email, String password, Set<Role> roles, boolean enabled) {
    this.username = username;
    this.email = email;
    this.password = password;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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
    return "User [username=" + username + ", email=" + email + ", password=" + password + ", roles="
        + roles + ", enabled=" + enabled + "]";
  } 
}
