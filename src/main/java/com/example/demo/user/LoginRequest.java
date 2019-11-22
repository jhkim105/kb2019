package com.example.demo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {

  private static final long serialVersionUID = 6729193854309660763L;

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
