package com.example.demo.searchlog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class SearchStatsRepositoryTest {

  @Autowired
  private SearchStatsRepository searchStatsRepository;

  @Test
  void testFindAllTop10ByOrderBySearchCountDesc() {
    assertFalse(searchStatsRepository.findAllTop10ByOrderBySearchCountDesc().isEmpty());
  }


}