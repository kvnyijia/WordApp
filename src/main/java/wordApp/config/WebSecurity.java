package wordApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import wordApp.filter.JWTAuthenticationFilter;
import wordApp.filter.JWTAuthorizationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurity {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((authz) -> 
      authz
        .requestMatchers(HttpMethod.POST, "/users").permitAll()
        .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
        .anyRequest().authenticated()
    );
    http.cors(Customizer.withDefaults());
    http.csrf(csrf -> csrf.disable());
    http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
    http.addFilter(new JWTAuthorizationFilter(authenticationManager(), new RestAuthenticationEntryPoint()));
    return http.build();
  }
  
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
