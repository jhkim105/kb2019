package com.example.demo.searchlog;

import com.example.demo.base.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class HotKeyword extends AbstractDto {

  private static final long serialVersionUID = -1149195182172465539L;

  private String keyword;
  private long searchCount;

  public static HotKeyword from(SearchStats searchStats) {
    return new HotKeyword(searchStats.getId(), searchStats.getSearchCount());
  }
}
