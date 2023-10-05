package wordApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import wordApp.entity.User;
import wordApp.rest.user.CreateUserReq;
import wordApp.rest.user.GetUserRes;
import wordApp.rest.user.UserNotFoundExp;
import wordApp.rest.user.UserUniqueViolationExp;
import wordApp.service.UserService;

@RestController
public class Controller {
  private UserService service;

  @Autowired
  public Controller(UserService theService) {
    this.service = theService;
  }

  @PostMapping(value="/users", produces=MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public Boolean addUser(@RequestBody CreateUserReq theUser) {
    User dbUser = service.find(theUser.getUsername());
    if (dbUser != null) {
      throw new UserUniqueViolationExp(theUser.getUsername());
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    User createdUser = new User(theUser.getUsername(), theUser.getEmail(), encoder.encode(theUser.getPassword()));
    service.save(createdUser);
    return true;
  }

  @GetMapping("/users/{username}")
  public GetUserRes getUser(@PathVariable String username) {
    User theUser = service.find(username);
    if (theUser == null) {
      throw new UserNotFoundExp(username);
    }
    return new GetUserRes(theUser.getUsername(), theUser.getEmail());
  }

  @PutMapping("/users")
  public User updateUser(@RequestBody User theUser) {
    User updatedUser = service.update(theUser);
    return updatedUser;
  }  

  @DeleteMapping("/users/{username}")
  public boolean deleteUser(@PathVariable String username) {
    if (service.find(username) == null) {
      throw new UserNotFoundExp(username);
    }
    service.delete(username);
    return true;
  }
}
