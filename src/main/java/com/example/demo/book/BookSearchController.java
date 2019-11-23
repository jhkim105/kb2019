package com.example.demo.book;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api
public class BookSearchController {

  private final BookSearchService bookSearchService;
  private final SearchJmsSender searchJmsSender;

  @GetMapping("/book-search")
  @ApiImplicitParam(name = "Authorization", value = "authToken", required = true, dataType = "string", paramType = "header")
  public ResponseEntity<Page<Book>> search(@RequestParam(required = false, defaultValue = "0") int page, int size,  String query) {
    Page<Book> bookPage = bookSearchService.search(PageRequest.of(page, size), query);
    searchJmsSender.send(query);
    return ResponseEntity.ok(bookPage);
  }

}
