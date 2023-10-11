package wordApp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wordApp.config.SecurityConstants;
import wordApp.entity.User;
import wordApp.rest.user.GetUserRes;
import wordApp.rest.user.LoginUserRes;
import wordApp.rest.user.UserErrorRes;
import wordApp.rest.user.UserUnauthorizedExp;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    setFilterProcessesUrl("/users/login"); 
  }

  @Override
  public Authentication attemptAuthentication(
    HttpServletRequest req, 
    HttpServletResponse res
  ) throws AuthenticationException {
    try {
      User creds = new ObjectMapper()
        .readValue(req.getInputStream(), User.class);
            
      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          creds.getUsername(),
          creds.getPassword(),
          new ArrayList<>()
        )
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(
    HttpServletRequest req,
    HttpServletResponse res,
    FilterChain chain,
    Authentication auth
  ) throws IOException {

    Object principal = auth.getPrincipal();
    String username = (String) principal;
    String token = JWT.create()
      .withSubject(username)
      .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
      .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

    GetUserRes theUser = new GetUserRes(username, "");
    LoginUserRes rbody = new LoginUserRes(token, theUser);
    String json = new Gson().toJson(rbody);
    res.setContentType("application/json");
    res.getWriter().write(json);
    res.getWriter().flush();
  }

  @Override
  protected void unsuccessfulAuthentication(
    HttpServletRequest req, 
    HttpServletResponse res,
    AuthenticationException failed
  ) throws IOException, ServletException {

    int statusCode = HttpStatus.UNAUTHORIZED.value();
    UserErrorRes rbody = new UserErrorRes(
      statusCode, 
      "incorrect username or password", 
      System.currentTimeMillis()
    );
    String json = new Gson().toJson(rbody);
    res.setContentType("application/json");
    res.setStatus(statusCode);
    res.getWriter().write(json);
    res.getWriter().flush();
  }
}
