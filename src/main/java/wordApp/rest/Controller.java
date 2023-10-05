package wordApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// import wordApp.Api;
import wordApp.entity.User;
import wordApp.service.UserService;

@RestController
public class Controller {
  // private Api myApi;

  // @Autowired
  // public Controller(Api theApi) {
  //   myApi = theApi;
  // }

  // @GetMapping("/hello")
  // public String getHello() {
  //   return myApi.sayHello();
  // }

  private UserService service;

  @Autowired
  public Controller(UserService theService) {
    this.service = theService;
  }

  @PostMapping("/users")
  public Boolean addUser(@RequestBody User theUser) {
    service.save(theUser);
    return true;
  }

  @GetMapping("/users/{username}")
  public User getUser(@PathVariable String username) {
    User theUser = service.find(username);
    if (theUser == null) {
      throw new UserNotFoundExp("User not found: " + username);
    }
    return theUser;
  }

  @PutMapping("/users")
  public User updateUser(@RequestBody User theUser) {
    User updatedUser = service.update(theUser);
    return updatedUser;
  }  

  @DeleteMapping("/users/{username}")
  public boolean deleteUser(@PathVariable String username) {
    if (service.find(username) == null) {
      throw new UserNotFoundExp("User not found: " + username);
    }
    service.delete(username);
    return true;
  }
}
