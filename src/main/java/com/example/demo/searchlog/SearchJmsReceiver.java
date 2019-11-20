package com.example.demo.searchlog;

import com.example.demo.base.JmsQueues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchJmsReceiver {

  private final SearchLogService searchLogService;

  @JmsListener(destination = JmsQueues.SEARCH + "-1", concurrency = "1")
  public void search1(String keyword) {
    log.debug("q1-keyword:{}", keyword);
    search(keyword);
  }

  @JmsListener(destination = JmsQueues.SEARCH + "-2", concurrency = "1")
  public void search2(String keyword) {
    log.debug("q2-keyword:{}", keyword);
    search(keyword);

  }

  @JmsListener(destination = JmsQueues.SEARCH + "-3", concurrency = "1")
  public void search3(String keyword) {
    log.debug("q3-keyword:{}", keyword);
    search(keyword);
  }


  private void search(String query) {
    searchLogService.save(query);
  }

}
