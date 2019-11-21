package com.example.demo.searchlog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchStatsRepository extends JpaRepository<SearchStats, String> {
  List<SearchStats> findAllTop10ByOrderBySearchCountDesc();
}
