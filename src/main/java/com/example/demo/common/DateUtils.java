package com.example.demo.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
  public static Long convertStringToTimestamp(String dateInString, String pattern) {
    LocalDate date = LocalDate.parse(dateInString, DateTimeFormatter.ofPattern(pattern));
    return date.toEpochDay();
  }
}
