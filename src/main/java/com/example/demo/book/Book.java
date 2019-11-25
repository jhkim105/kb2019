package com.example.demo.book;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {
  private String title;
  private String thumbnail;
  private String description;
  private String isbn;
  private String author;
  private String publisher;
  private Long publishedDate;
  private Integer price;
  private Integer salePrice;

  @Builder
  public Book(String title, String thumbnail, String description, String isbn, String author, String publisher,
      Long publishedDate, Integer price, Integer salePrice) {
    this.title = title;
    this.thumbnail = thumbnail;
    this.description = description;
    this.isbn = isbn;
    this.author = author;
    this.publisher = publisher;
    this.publishedDate = publishedDate;
    this.price = price;
    this.salePrice = salePrice;
  }

}
