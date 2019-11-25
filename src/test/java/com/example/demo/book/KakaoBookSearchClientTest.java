package com.example.demo.book;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Slf4j
class KakaoBookSearchClientTest {

  @Autowired
  private KakaoBookSearchClient kakaoBookSearchClient;

  @Test
  @DisplayName("카카오 도서검색")
  void search() {
    String keyword = "미움받을 용기";
    Page<Book> page = kakaoBookSearchClient.search(PageRequest.of(0, 10), keyword);
    log.debug("{}", page.getContent());
    assertFalse(page.getContent().isEmpty());
  }

  @Test
  @DisplayName("카카오 도서검색 - 페이징 잘 되는지")
  void search_pagination() {
    String keyword = "미움받을 용기";
    Page<Book> page = kakaoBookSearchClient.search(PageRequest.of(0, 2), keyword);
    Page<Book> page2 = kakaoBookSearchClient.search(PageRequest.of(1, 2), keyword);
    assertNotEquals(page.getContent().get(0).getIsbn(), page2.getContent().get(0).getIsbn());
  }
}