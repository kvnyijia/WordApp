package wordApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/users/{username}")
  public User getUser(@PathVariable String username) {
    return service.find(username);
  }
}
