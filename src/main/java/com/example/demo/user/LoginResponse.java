package com.example.demo.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

  private static final long serialVersionUID = 3417144000325211448L;

  private String authToken;

  @Builder
  public LoginResponse(String authToken) {
    this.authToken = authToken;
  }
}
