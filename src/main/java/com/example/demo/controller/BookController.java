package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookSearchService;
import com.example.demo.service.SearchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

  private final BookSearchService bookSearchService;
  private final SearchLogService searchLogService;

  @GetMapping("/search")
  public ResponseEntity<Page<Book>> search(@PageableDefault(page = 0, size = 10) Pageable pageable, String keyword) {
    Page<Book> page = bookSearchService.search(pageable, keyword);
    searchLogService.save(keyword);
    return ResponseEntity.ok(page);
  }

}
