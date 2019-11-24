package com.example.demo.book;

import com.example.demo.TestData;
import com.example.demo.common.SearchMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchJmsSenderTest {

  @Autowired
  private SearchJmsSender searchJmsSender;


  @Test
  public void send() {
    String keyword = "테스트";
    searchJmsSender.send(SearchMessage.builder().keyword(keyword).searchedBy(TestData.User.ID).build());
  }

}