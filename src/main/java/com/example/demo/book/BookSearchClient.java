package com.example.demo.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookSearchClient {
  Page<Book> search(Pageable pageable, String keyword);
}
