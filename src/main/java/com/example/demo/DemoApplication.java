package com.example.demo;

import com.example.demo.config.KakaoProperties;
import com.example.demo.config.NaverProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({ KakaoProperties.class, NaverProperties.class})
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }


  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
