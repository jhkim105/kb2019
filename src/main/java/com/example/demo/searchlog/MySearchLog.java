package com.example.demo.searchlog;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class MySearchLog {
  @NonNull  private String keyword;
  @NonNull  private Date createdDate;

  public static MySearchLog from(SearchLog searchLog) {
    return new MySearchLog(searchLog.getKeyword(), searchLog.getCreatedDate());
  }
}
