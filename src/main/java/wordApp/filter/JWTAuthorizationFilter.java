package wordApp.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import wordApp.config.SecurityConstants;
import wordApp.rest.user.UserErrorRes;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
  
  public JWTAuthorizationFilter(AuthenticationManager authManager) {
    super(authManager);
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest req,
    HttpServletResponse res,
    FilterChain chain
  ) throws IOException, ServletException {
    
    String header = req.getHeader(SecurityConstants.HEADER_STRING);
    if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  // Reads the JWT from the Authorization header, and then uses JWT to validate the token
  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws AuthenticationException {
    String token = request.getHeader(SecurityConstants.HEADER_STRING);

    try {
      if (token != null) {
        // parse the token.
        String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
          .build()
          .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
          .getSubject();

        if (user != null) {
          // new arraylist means authorities
          return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
        return null;
      }
    } catch (Exception e) {
      System.out.println(e);
      // throw new UserUnauthorizedExp("Invalid token");
      // throw new SignatureVerificationException(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

      // throw new AuthenticationException("");

      throw new RuntimeException(e);

      // throw new BadCredentialsException("Invalid token");
    }
    return null;
  }

  @Override
  protected void onUnsuccessfulAuthentication(
    HttpServletRequest req, 
    HttpServletResponse res,
    AuthenticationException failed
  ) throws IOException {

    int statusCode = HttpStatus.UNAUTHORIZED.value();
    UserErrorRes rbody = new UserErrorRes(
      statusCode, 
      "Invalid token", 
      System.currentTimeMillis()
    );
    String json = new Gson().toJson(rbody);
    res.setContentType("application/json");
    res.setStatus(statusCode);
    res.getWriter().write(json);
    res.getWriter().flush();
  }
}
