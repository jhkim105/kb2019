package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString(exclude = {"password", "confirmPassword"})
public class JoinRequest {
  private String username;
  private String password;
  private String confirmPassword;

  public User toUser(PasswordEncoder passwordEncoder) {
    return User.builder()
        .username(username)
        .password(passwordEncoder.encode(password))
        .role(Role.USER)
        .build();
  }
}
