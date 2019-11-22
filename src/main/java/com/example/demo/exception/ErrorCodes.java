package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
  SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 999),
  INVALID_TOKEN(HttpStatus.BAD_REQUEST, 101),
  AUTHENTICATION_NEED(HttpStatus.UNAUTHORIZED, 102)
  ;

  private final HttpStatus status;
  private final int code;

  ErrorCodes(HttpStatus status, int code) {
    this.status = status;
    this.code = code;
  }

}
