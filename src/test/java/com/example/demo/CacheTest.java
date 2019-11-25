package com.example.demo;

import com.example.demo.book.BookSearchClient;
import com.example.demo.searchlog.SearchLogService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class CacheTest {

  @Autowired
  SearchLogService searchLogService;

  @Autowired
  BookSearchClient kakaoBookSearchClient;

  @Autowired
  BookSearchClient naverBookSearchClient;


  @Test
  @Disabled("통합테스트시 실패. 캐시데이터 조회로 건수가 맞지않음")
  void testHotKeyword() {
    searchLogService.getHotKeywordList();
    searchLogService.getHotKeywordList();
    searchLogService.getHotKeywordList();
    searchLogService.getHotKeywordList();
  }


  @Test
  void testKakaoSearch() {
    kakaoBookSearchClient.search(PageRequest.of(1, 2), "11");
    kakaoBookSearchClient.search(PageRequest.of(1, 2), "11");
    kakaoBookSearchClient.search(PageRequest.of(1, 2), "11");

  }


}
