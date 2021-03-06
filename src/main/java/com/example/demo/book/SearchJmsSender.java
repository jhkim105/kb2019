package com.example.demo.book;

import com.example.demo.common.JmsQueues;
import com.example.demo.common.SearchMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchJmsSender {
  private final JmsTemplate jmsTemplate;

  public void send(SearchMessage message) {
    String keyword = message.getKeyword();
    int queueNumber = keyword.hashCode() % JmsQueues.SEARCH_QUEUE_COUNT;
    String queueName = String.format("%s-%s", JmsQueues.SEARCH, queueNumber + 1);
    jmsTemplate.convertAndSend(queueName, message);
  }

}
