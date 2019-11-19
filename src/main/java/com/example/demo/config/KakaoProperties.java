package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kakao")
@Getter
@Setter
@ToString
public class KakaoProperties {
  private String appKey;
  private String apiUrl;
}
