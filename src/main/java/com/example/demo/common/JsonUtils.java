package com.example.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils {

  public static String toString(Object obj) {
    ObjectMapper mapper = new ObjectMapper();
    String result;
    try {
      result = mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(String.format("convert to json string error..error:%s, object:%s",
          e.toString(), obj.toString()), e);
    }
    return result;
  }

}
