package com.example.demo.book;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Slf4j
class NaverBookSearchClientTest {

  @Autowired
  NaverBookSearchClient naverBookSearchClient;

  @Test
  @DisplayName("네이버 도서검색")
  void search() {
    String keyword = "미움받을 용기";
    Page<Book> page = naverBookSearchClient.search(PageRequest.of(0, 10), keyword);
    log.debug("{}", page.getContent());
    assertFalse(page.getContent().isEmpty());
  }

}