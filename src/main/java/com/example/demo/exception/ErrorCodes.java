package com.example.demo.exception;

import lombok.Getter;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
  SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 999, "시스템 오류가 발생했습니다."),
  INVALID_TOKEN(HttpStatus.BAD_REQUEST, 101, "인증토큰이 잘못되었습니다."),
  AUTHENTICATION_NEED(HttpStatus.UNAUTHORIZED, 102, "로그인이 필요합니다."),
  AUTHENTICATION_FAIL(HttpStatus.BAD_REQUEST, 103, "사용자 정보가 없거나 잘못된 정보입니다")
  ;

  private final HttpStatus status;
  private final int code;
  private final String message;

  ErrorCodes(HttpStatus status, int code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

}
