package com.example.demo.searchlog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {
  Page<SearchLog> findAllByCreatedByOrderByIdDesc(Pageable pageable, Long id);
}
