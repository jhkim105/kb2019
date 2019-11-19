package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookSearchServiceKakao implements BookSearchService {

  @Override
  public Page<Book> search(Pageable pageable, String keyword) {
    return null;
  }
}
