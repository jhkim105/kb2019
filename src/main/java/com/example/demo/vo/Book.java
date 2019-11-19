package com.example.demo.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {
  private String title;
  private String thumbnail;
  private String description;
  private String isbn;
  private List<String> authors;
  private String publisher;
  private Long publishedDate;
  private Long price;
  private Long salePrice;

  @Builder
  public Book(String title, String thumbnail, String description, String isbn, List<String> authors, String publisher,
      Long publishedDate, Long price, Long salePrice) {
    this.title = title;
    this.thumbnail = thumbnail;
    this.description = description;
    this.isbn = isbn;
    this.authors = authors;
    this.publisher = publisher;
    this.publishedDate = publishedDate;
    this.price = price;
    this.salePrice = salePrice;
  }

}
