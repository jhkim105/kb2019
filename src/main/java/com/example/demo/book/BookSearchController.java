package com.example.demo.book;

import com.example.demo.common.SearchMessage;
import com.example.demo.security.SecurityUtils;
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

  private final BookSearchService kakaoBookSearchService;
  private final BookSearchService naverBookSearchService;
  private final SearchJmsSender searchJmsSender;

  @GetMapping("/book-search")
  @ApiImplicitParam(name = "Authorization", value = "authToken", required = true, dataType = "string", paramType = "header")
  public ResponseEntity<Page<Book>> search(@RequestParam(required = false, defaultValue = "0") int page, int size,  String query) {
    Page<Book> bookPage = kakaoBookSearchService.search(PageRequest.of(page, size), query);
    searchJmsSender.send(SearchMessage.builder()
        .keyword(query)
        .searchedBy(SecurityUtils.getAuthUser().getId())
        .build());
    return ResponseEntity.ok(bookPage);
  }

}
