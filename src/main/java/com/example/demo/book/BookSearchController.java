package com.example.demo.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookSearchController {

  private final BookSearchService bookSearchService;
  private final SearchJmsSender searchJmsSender;

  @GetMapping("/book-search")
  public ResponseEntity<Page<Book>> search(@PageableDefault(page = 0, size = 10) Pageable pageable, String query) {
    Page<Book> page = bookSearchService.search(pageable, query);
    searchJmsSender.send(query);
    return ResponseEntity.ok(page);
  }

}
