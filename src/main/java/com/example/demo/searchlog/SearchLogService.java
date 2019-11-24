package com.example.demo.searchlog;

import com.example.demo.security.AuthUser;
import com.example.demo.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchLogService {

  private final SearchLogRepository searchLogRepository;
  private final SearchStatsRepository searchStatsRepository;

  @Transactional
  public void save(String keyword, Long searchedBy) {
    saveSearchLog(keyword, searchedBy);
    saveSearchStats(keyword);
  }

  private SearchLog saveSearchLog(String keyword, Long searchedBy) {
    SearchLog searchLog = SearchLog.builder()
        .keyword(keyword)
        .createdBy(searchedBy)
        .build();
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

  public Page<MySearchLog> getMyList(Pageable pageable) {
    AuthUser authUser = SecurityUtils.getAuthUser();
    Page<SearchLog> searchLogPage = searchLogRepository.findAllByCreatedByOrderByIdDesc(pageable, authUser.getId());

    List<MySearchLog> mySearchLogs = searchLogPage.getContent().stream().map(MySearchLog::from).collect(Collectors.toList());
    return new PageImpl<>(mySearchLogs, pageable, searchLogPage.getTotalElements());
  }

  public Page<HotKeyword> getHotKeywordList() {
    List<SearchStats> searchStats = searchStatsRepository.findAllTop10ByOrderBySearchCountDesc();
    List<HotKeyword> hotKeywords = searchStats.stream().map(HotKeyword::from).collect(Collectors.toList());
    return new PageImpl<>(hotKeywords);
  }
}
