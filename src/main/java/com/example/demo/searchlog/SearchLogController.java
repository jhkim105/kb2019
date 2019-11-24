package com.example.demo.searchlog;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search-logs")
@Api
public class SearchLogController {

  private final SearchLogService searchLogService;

  @GetMapping("/my")
  public ResponseEntity<Page<MySearchLog>> my(@RequestParam(required = false, defaultValue = "0") int page, int size) {
    Page<MySearchLog> mySearchLogPage = searchLogService.getMyList(PageRequest.of(page, size));
    return ResponseEntity.ok(mySearchLogPage);
  }

  @GetMapping("/hot-keyword")
  public ResponseEntity<Page<HotKeyword>> hotKeyword() {
    Page<HotKeyword> page = searchLogService.getHotKeywordList();
    return ResponseEntity.ok(page);
  }

}
