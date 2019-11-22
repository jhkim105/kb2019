package com.example.demo.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = IntegrationTestConfig.class)
public class IntegrationTests {
  @Autowired
  protected MockMvc mockMvc;

}
