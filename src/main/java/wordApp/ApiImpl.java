package wordApp;

import org.springframework.stereotype.Component;

@Component
public class ApiImpl implements Api {
  @Override
  public String sayHello() {
    return "hello";
  }
}
