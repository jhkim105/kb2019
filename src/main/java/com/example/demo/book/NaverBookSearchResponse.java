package com.example.demo.book;

import com.example.demo.common.DateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class NaverBookSearchResponse {
  private int total;
  private int start;
  private int display;
  private List<Item> items;


  public Page<Book> toBookPage(Pageable pageable) {
    List<Book> books = items.stream().map(NaverBookSearchResponse.Item::toBook).collect(Collectors.toList());

    return new PageImpl<>(books, pageable, total);
  }

  @Getter
  @Setter
  @ToString
  public static class Item {
    private String title;
    private String link;
    private String image;
    private String author;
    private Integer price;
    private Integer discount;
    private String publisher;
    private String isbn;
    private String description;
    private String pubdate; //yyyyMMdd

    private Integer getSalePrice() {
      if (price == null)
        return null;
      if (discount == null)
        return price;

      return price - discount;
    }

    public Book toBook() {
      return Book.builder()
          .author(author)
          .description(description)
          .publishedDate(DateUtils.convertStringToTimestamp(pubdate, "yyyyMMdd"))
          .isbn(isbn)
          .price(price)
          .publisher(publisher)
          .salePrice(getSalePrice())
          .thumbnail(image)
          .title(title)
          .build();
    }
  }
}
