package com.example.demo.searchlog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchLogService {

  private final SearchLogRepository searchLogRepository;
  private final SearchStatsRepository searchStatsRepository;

  @Transactional
  public void save(String keyword) {
    saveSearchLog(keyword);
    saveSearchStats(keyword);
  }

  private SearchLog saveSearchLog(String keyword) {
    SearchLog searchLog = SearchLog.builder().keyword(keyword).build();
    return searchLogRepository.save(searchLog);
  }


  private SearchStats saveSearchStats(String keyword) {
    SearchStats searchStats;
    Optional<SearchStats> optionalSearchStats = searchStatsRepository.findById(keyword);
    if (optionalSearchStats.isPresent()) {
      searchStats = optionalSearchStats.get();
    } else {
      searchStats = SearchStats.builder().id(keyword).build();
    }
    searchStats.addCount();
    return searchStatsRepository.save(searchStats);
  }
}
