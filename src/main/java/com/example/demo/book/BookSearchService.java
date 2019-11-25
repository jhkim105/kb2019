package com.example.demo.book;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookSearchService {

  private final BookSearchClient kakaoBookSearchClient;

  private final BookSearchClient naverBookSearchClient;

  @HystrixCommand(fallbackMethod = "searchUsingNaver")
  public Page<Book> search(Pageable pageable, String keyword) {
    log.debug("search from kakao");
    return kakaoBookSearchClient.search(pageable, keyword);
  }

  public Page<Book> searchUsingNaver(Pageable pageable, String keyword) {
    log.debug("search from naver");
    return naverBookSearchClient.search(pageable, keyword);
  }
}
