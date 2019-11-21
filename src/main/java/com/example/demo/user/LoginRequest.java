package com.example.demo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter @Setter
@RequiredArgsConstructor
public class LoginRequest {
  @NonNull
  @ApiModelProperty(example = "user")
  private String username;

  @NonNull
  @ApiModelProperty(example = "111111")
  private String password;

  public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(this.username, this.password);
    return token;
  }
}
