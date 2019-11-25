package com.example.demo.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


class DateUtilsTest {

  @Test
  public void test() {
    long time = DateUtils.convertStringToTimestamp("20190101", "yyyyMMdd");
    assertNotEquals(time, 1574640000000l);
  }
}