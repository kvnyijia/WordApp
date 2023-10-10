package wordApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import wordApp.service.UserService;

@Configuration
public class WebSecurity {
  private UserService userService;
  private BCryptPasswordEncoder encoder;

  public WebSecurity(UserService userService, BCryptPasswordEncoder encoder) {
    this.userService = userService;
    this.encoder = encoder;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((authz) -> 
      authz
        .requestMatchers(HttpMethod.POST, "/users").permitAll()
        .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
        .requestMatchers(HttpMethod.GET, "/users/**").authenticated()
    );
    
    // http.httpBasic(Customizer.withDefaults());
    http.cors(Customizer.withDefaults());
    http.csrf(csrf -> csrf.disable());
    return http.build();
  }
}
