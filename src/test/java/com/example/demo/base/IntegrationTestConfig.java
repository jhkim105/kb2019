package com.example.demo.base;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@TestConfiguration
public class IntegrationTestConfig {

  @Bean
  protected MockMvc mockMvc(WebApplicationContext ctx) {
    return MockMvcBuilders.webAppContextSetup(ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true))
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .alwaysDo(print())
        .build();
  }

}
