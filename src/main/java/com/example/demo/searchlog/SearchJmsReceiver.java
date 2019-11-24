package com.example.demo.searchlog;

import com.example.demo.common.JmsQueues;
import com.example.demo.common.SearchMessage;
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
  public void search1(SearchMessage searchMessage) {
    log.debug("q1-keyword:{}", searchMessage);
    search(searchMessage);
  }

  @JmsListener(destination = JmsQueues.SEARCH + "-2", concurrency = "1")
  public void search2(SearchMessage searchMessage) {
    log.debug("q2-keyword:{}", searchMessage);
    search(searchMessage);

  }

  @JmsListener(destination = JmsQueues.SEARCH + "-3", concurrency = "1")
  public void search3(SearchMessage searchMessage) {
    log.debug("q3-keyword:{}", searchMessage);
    search(searchMessage);
  }


  private void search(SearchMessage searchMessage) {
    searchLogService.save(searchMessage.getKeyword(), searchMessage.getSearchedBy());
  }

}
