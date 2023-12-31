package wordApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import wordApp.rest.user_class.CreateUserReq;
import wordApp.rest.user_class.GetUserRes;
import wordApp.rest.user_class.LoginUserReq;
import wordApp.rest.user_class.LoginUserRes;
import wordApp.rest.user_class.UserNotFoundExp;
import wordApp.rest.user_class.UserUnauthorizedExp;
import wordApp.rest.user_class.UserUniqueViolationExp;
import wordApp.service.UserService;

@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {
  private UserService service;

  @Autowired
  public UserController(UserService theService) {
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

  // @PostMapping(value="/users/login", produces=MediaType.APPLICATION_JSON_VALUE)
  // @ResponseBody
  // @ResponseStatus(HttpStatus.CREATED)
  // public LoginUserRes loginUser(@RequestBody LoginUserReq theUser) {
  //   User dbUser = service.find(theUser.getUsername());
  //   if (dbUser == null) {
  //     throw new UserNotFoundExp(theUser.getUsername());
  //   }

  //   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  //   String hash = dbUser.getPassword();
  //   boolean isMatch = encoder.matches(theUser.getPassword(), hash);
  //   if (!isMatch) {
  //     throw new UserUnauthorizedExp("Incorrect password");
  //   }
  //   System.out.println(">>> Match!!");

  //   return new LoginUserRes("tmp_token", new GetUserRes(dbUser.getUsername(), dbUser.getEmail()));
  // }
}
