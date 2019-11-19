package com.example.demo.controller;

import com.example.demo.vo.Book;
import com.example.demo.service.BookSearchService;
import com.example.demo.service.SearchLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private BookSearchService bookSearchService;

  @MockBean
  private SearchLogService searchLogService;


  @Test
  void search() throws Exception {
    // given
    String keyword = "클린코드";
    int page = 0;
    int size = 5;
    Pageable pageable = PageRequest.of(page, size);
    int total = 2;
    Book book1 = newBook(1);
    Book book2 = newBook(2);
    given(this.bookSearchService.search(pageable, keyword)).willReturn(new PageImpl<>(Arrays.asList(book1, book2), pageable, 2));

    // when
    ResultActions resultActions = this.mvc.perform(get("/books/search")
        .param("page", String.valueOf(page))
        .param("size", String.valueOf(size))
        .param("keyword", keyword)
        .accept(MediaType.APPLICATION_JSON));
    resultActions.andDo(print());

    // then
    resultActions.andExpect(status().isOk())
      .andExpect(jsonPath("$.content", hasSize(total)))
      .andExpect(jsonPath("$.totalElements").value(total))
      .andExpect(jsonPath("$.content[0].title").value(book1.getTitle()))
      .andExpect(jsonPath("$.content[0].thumbnail").value(book1.getThumbnail()))
      .andExpect(jsonPath("$.content[0].description").value(book1.getDescription()))
      .andExpect(jsonPath("$.content[0].isbn").value(book1.getIsbn()))
      .andExpect(jsonPath("$.content[0].publisher").value(book1.getPublisher()))
      .andExpect(jsonPath("$.content[0].publishedDate").value(book1.getPublishedDate()))
      .andExpect(jsonPath("$.content[0].price").value(book1.getPrice()))
      .andExpect(jsonPath("$.content[0].salePrice").value(book1.getSalePrice()));

    }

  private Book newBook(int index) {
    return Book.builder()
        .title("title" + index)
        .thumbnail("thumbnail" + index)
        .description("description" + index)
        .isbn("isbn" + index)
        .publisher("publisher" + index)
        .publishedDate(new Date().getTime())
        .price(index * 10000l)
        .salePrice(index * 10000l - 1000l)
        .build();
  }
}
