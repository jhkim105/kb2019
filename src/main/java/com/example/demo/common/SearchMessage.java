package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SearchMessage implements Serializable {
  private static final long serialVersionUID = -2878895548467201129L;
  private String keyword;
  private Long searchedBy;

  @Builder
  public SearchMessage(String keyword, Long searchedBy) {
    this.keyword = keyword;
    this.searchedBy = searchedBy;
  }
}
