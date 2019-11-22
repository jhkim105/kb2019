package com.example.demo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter @ToString
@NoArgsConstructor
public class LoginResponse implements Serializable {

  private static final long serialVersionUID = 3417144000325211448L;

  private String authToken;

  @Builder
  public LoginResponse(String authToken) {
    this.authToken = authToken;
  }
}
