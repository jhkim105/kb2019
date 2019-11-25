package com.example.demo;

import com.example.demo.searchlog.SearchLogService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheTest {

  @Autowired SearchLogService searchLogService;

  @Test
  @Disabled
  void test() {
    searchLogService.getHotKeywordList();
    searchLogService.getHotKeywordList();
    searchLogService.getHotKeywordList();
    searchLogService.getHotKeywordList();
  }
}
