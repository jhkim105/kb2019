package com.example.demo.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class KakaoBookSearchResponse {

  private Meta meta;

  private List<Document> documents;

  public Page<Book> toBookPage(Pageable pageable) {
    List<Book> books = documents.stream().map(Document::toBook).collect(Collectors.toList());

    return new PageImpl<>(books, pageable, meta.totalCount);
  }

  @Getter
  @Setter
  @ToString
  public static class Meta {
    @JsonProperty("total_count") private int totalCount;
    @JsonProperty("pageable_count") private int pageableCount;
    @JsonProperty("is_end") private boolean end;
  }

  @Getter
  @Setter
  @ToString
  public static class Document {
    private List<String> authors;
    private String contents;
    private Date datetime;
    private String isbn;
    private long price;
    private String publisher;
    @JsonProperty("sale_price") private long salePrice;
    private String status;
    private String thumbnail;
    private String title;
    private List<String> translators;
    private String url;

    public Book toBook() {
      return Book.builder()
          .authors(authors)
          .description(contents)
          .publishedDate(datetime.getTime())
          .isbn(isbn)
          .price(price)
          .publisher(publisher)
          .salePrice(salePrice)
          .thumbnail(thumbnail)
          .title(title)
          .build();
    }

  }
}
