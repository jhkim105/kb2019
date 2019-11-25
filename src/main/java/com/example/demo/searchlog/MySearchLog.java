package com.example.demo.searchlog;

import com.example.demo.base.AbstractDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor
public class MySearchLog extends AbstractDto {

  private static final long serialVersionUID = -9135135455649087808L;

  @NonNull  private String keyword;
  @NonNull  private Date createdDate;

  public static MySearchLog from(SearchLog searchLog) {
    return new MySearchLog(searchLog.getKeyword(), searchLog.getCreatedDate());
  }
}
