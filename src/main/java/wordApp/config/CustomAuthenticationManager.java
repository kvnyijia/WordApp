package wordApp.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import wordApp.entity.User;
import wordApp.rest.user.UserUnauthorizedExp;
import wordApp.service.UserService;

@Configuration
@Component
public class CustomAuthenticationManager implements AuthenticationManager {
  // @Autowired
  // private UserDetailsService customUserDetailsService;

  // public CustomAuthenticationManager() {}

  // @Autowired
  // public CustomAuthenticationManager(UserDetailsService customUserDetailsService) {
  //   this.customUserDetailsService = customUserDetailsService;
  // }

  @Autowired
  private UserService service;

  // @Autowired
  // public CustomAuthenticationManager(UserService theService) {
  //   this.service = theService;
  // }

  @Bean
  protected PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // final UserDetails userDetail = customUserDetailsService.loadUserByUsername(authentication.getName());
    final User userDetail = service.find(authentication.getName());
    if (userDetail == null) {
      // throw new UserUnauthorizedExp("incorrect username or password");
      throw new BadCredentialsException("Wrong password");
    }
    if (!passwordEncoder().matches(authentication.getCredentials().toString(), userDetail.getPassword())) {
      throw new BadCredentialsException("Wrong password");
    }
    // return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
    return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), new ArrayList<>());
  }
}
