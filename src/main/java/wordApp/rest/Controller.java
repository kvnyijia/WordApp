package wordApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import wordApp.Api;

@RestController
public class Controller {
  private Api myApi;

  @Autowired
  public Controller(Api theApi) {
    myApi = theApi;
  }

  @GetMapping("/hello")
  public String getHello() {
    return myApi.sayHello();
  }
}
