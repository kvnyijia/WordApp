package wordApp.rest.security.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wordApp.rest.security.SecurityConstants;

class MyAuthenticationException extends AuthenticationException {

  public MyAuthenticationException(String msg) {
    super(msg);
  }
}

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
  
  public JWTAuthorizationFilter(AuthenticationManager authManager, AuthenticationEntryPoint entryPoint) {
    super(authManager, entryPoint);
  }

  /**
   * This method intercepts the requests then invokes `getAuthentication`
   * to checks the Authorization header.
   */
  @Override
  protected void doFilterInternal(
    HttpServletRequest req,
    HttpServletResponse res,
    FilterChain chain
  ) throws IOException, ServletException {

    // Explicitly states POST /users do not go thru Authorization Filter
    if (req.getMethod().equals("POST") && req.getRequestURI().equals("/users")) {
      chain.doFilter(req, res);
      return;
    }
    
    try {
      UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(req, res);
    } catch (AuthenticationException e) {
      getAuthenticationEntryPoint().commence(req, res, e);
    }
  }

  /**
   * This method reads the token from the Authorization header (if presents), 
   * and then uses JWT to validate the token. If the token is valid, it 
   * returns an access token which Spring will use internally.
   * 
   * @param request
   * @return
   * @throws AuthenticationException
   */
  private UsernamePasswordAuthenticationToken getAuthentication(
    HttpServletRequest request
  ) throws AuthenticationException {

    String token = request.getHeader(SecurityConstants.HEADER_STRING);
    if (token == null) {
      throw new MyAuthenticationException("Authentication token not found");
    }
    if (!token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      throw new MyAuthenticationException("Invalid token: unsupported authentication scheme");
    }

    DecodedJWT decodedJWT;
    try {
      decodedJWT = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
        .build()
        .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
    } catch (JWTVerificationException e) {
      throw new MyAuthenticationException("Invalid token: " + e.getMessage());
    }

    String user = decodedJWT.getSubject();
    if (user == null) {
      return null;
    }
    // new arraylist means authorities
    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
  }
}
