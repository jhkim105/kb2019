package com.example.demo.book;

import com.example.demo.book.BookSearchServiceKakao;
import com.example.demo.book.Book;
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
class BookSearchServiceKakaoTest {

  @Autowired
  private BookSearchServiceKakao bookSearchServiceKakao;

  @Test
  @DisplayName("카카오 도서검색")
  void search() {
    String keyword = "미움받을 용기";
    Page<Book> page = bookSearchServiceKakao.search(PageRequest.of(0, 10), keyword);
    log.debug("{}", page.getContent());
    assertFalse(page.getContent().isEmpty());
  }
}