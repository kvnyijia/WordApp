package wordApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import wordApp.filter.JWTAuthenticationFilter;
import wordApp.filter.JWTAuthorizationFilter;
import wordApp.service.UserService;
import wordApp.service.UserServiceImpl;

@Configuration
@EnableMethodSecurity
public class WebSecurity {
  // private UserService userService;
  // private BCryptPasswordEncoder encoder;

  // public WebSecurity(UserService userService, BCryptPasswordEncoder encoder) {
  //   this.userService = userService;
  //   this.encoder = encoder;
  // }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((authz) -> 
      authz
        .requestMatchers(HttpMethod.POST, "/users").permitAll()
        .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
        .anyRequest().authenticated()
    );
    
    // http.httpBasic(Customizer.withDefaults());
    http.cors(Customizer.withDefaults());
    http.csrf(csrf -> csrf.disable());

    http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
    http.addFilter(new JWTAuthorizationFilter(authenticationManager()));

    // http.addFilter(new JWTAuthenticationFilter(new CustomAuthenticationManager()));
    // http.addFilter(new JWTAuthorizationFilter(new CustomAuthenticationManager()));

    // http.addFilter(new JWTAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))));
    // http.addFilter(new JWTAuthorizationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))));

    return http.build();
  }

  // @Bean
  // public AuthenticationManager authenticationManagerBean() throws Exception {
  //   return authenticationManagerBean();
  // }

  // @Bean
  // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
  //   return authenticationConfiguration.getAuthenticationManager();
  // }
  
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return new CustomAuthenticationManager();
  }

  /**
   * This CORS bean is needed for /users/login
   * @return
   */
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
