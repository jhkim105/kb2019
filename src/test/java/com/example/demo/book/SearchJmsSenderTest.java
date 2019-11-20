package com.example.demo.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchJmsSenderTest {

  @Autowired
  private SearchJmsSender searchJmsSender;


  @Test
  public void send() {
    searchJmsSender.send("테스트 주도 개발");
  }

}