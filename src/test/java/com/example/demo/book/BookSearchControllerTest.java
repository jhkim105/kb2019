package com.example.demo.book;

import com.example.demo.base.ControllerTests;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookSearchControllerTest extends ControllerTests {

  @MockBean
  private BookSearchService bookSearchService;

  @MockBean
  private SearchJmsSender searchJmsSender;

  @Test
  @WithMockUser(value = "user")
  @Disabled("spring security error")
  void search() throws Exception {
    // given
    String query = "클린코드";
    int page = 0;
    int size = 5;
    Pageable pageable = PageRequest.of(page, size);
    int total = 2;
    Book book1 = newBook(1);
    Book book2 = newBook(2);
    given(this.bookSearchService.search(pageable, query)).willReturn(new PageImpl<>(Arrays.asList(book1, book2), pageable, 2));
//    given(SecurityUtils.getAuthUser().getId()).willReturn(TestData.User.ID);
    // when
    ResultActions resultActions = this.mvc.perform(get("/book-search")
        .param("page", String.valueOf(page))
        .param("size", String.valueOf(size))
        .param("query", query)
        .param("trace", "true")
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
        .price(index * 10000)
        .salePrice(index * 10000 - 1000)
        .build();
  }
}
