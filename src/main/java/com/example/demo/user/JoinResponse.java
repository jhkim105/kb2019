package com.example.demo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor()
@Getter
public class JoinResponse {
  private Long id;
  private String username;

  @Builder
  public JoinResponse(Long id, String username) {
    this.id = id;
    this.username = username;
  }

  public static JoinResponse from(User user) {
    return JoinResponse.builder()
        .id(user.getId())
        .username(user.getUsername())
        .build();
  }
}
