package com.example.demo.searchlog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HotKeyword {
  private String keyword;
  private long count;

  public static HotKeyword from(SearchStats searchStats) {
    return new HotKeyword(searchStats.getId(), searchStats.getSearchCount());
  }
}
