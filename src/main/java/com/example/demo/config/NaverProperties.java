package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("naver")
@Getter
@Setter
@ToString
public class NaverProperties {
  private String clientId;
  private String clientSecret;
  private String apiUrl;
}
