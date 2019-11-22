package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtAuthenticationTokenService {
  private JWTVerifier jwtVerifier;
  private Algorithm algorithm;

  public JwtAuthenticationTokenService(@Value("${jwt.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
    this.jwtVerifier = JWT.require(algorithm).build();
  }

  public String generateToken(AuthUser authUser) {
    ZonedDateTime today = ZonedDateTime.now();
    Date expireDate = Date.from(today.plusDays(1).toInstant());
    return generateToken(authUser, expireDate);
  }

  public String generateRefreshToken(AuthUser authUser) {
    ZonedDateTime today = ZonedDateTime.now();
    Date expireDate = Date.from(today.plusDays(7).toInstant());
    return generateToken(authUser, expireDate);
  }


  public String generateToken(AuthUser authUser, Date expireDate) {
    Date now = new Date();
    JWTCreator.Builder builder = JWT.create()
        .withClaim("id", authUser.getId())
        .withClaim("username", authUser.getUsername())
        .withClaim("authority", authUser.getAuthority())
        .withIssuedAt(now);

    if (expireDate != null)
      builder.withExpiresAt(expireDate);

    String token = builder.sign(algorithm);
    log.debug("token:{}", token);
    return token;
  }

  public void checkToken(String token) {
    try {
      jwtVerifier.verify(token);
    } catch (JWTVerificationException ex) {
      throw new BusinessException(ErrorCodes.INVALID_TOKEN, ex);
    }

  }

  public AuthUser parseToken(String token) {
    checkToken(token);
    try {
      DecodedJWT jwt = JWT.decode(token);
      Long id = jwt.getClaim("id").asLong();
      String username = jwt.getClaim("username").asString();
      String authority = jwt.getClaim("authority").asString();
      AuthUser authUser = AuthUser.builder().id(id).username(username).authority(authority).build();
      return authUser;
    } catch (JWTDecodeException ex) {
      throw new BusinessException(ErrorCodes.INVALID_TOKEN, ex);
    }
  }
}
