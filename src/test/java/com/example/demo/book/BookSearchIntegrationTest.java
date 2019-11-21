package com.example.demo.book;

import com.example.demo.user.LoginRequest;
import com.example.demo.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BookSearchIntegrationTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext ctx;

  @Autowired
  private UserService userService;

  private String authToken;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
        .alwaysDo(print())
        .build();

    LoginRequest loginRequest = new LoginRequest("user", "111111");
    authToken = userService.login(loginRequest).getAuthToken();
  }

  @Test
  void search() throws Exception {

    // when
    String query = "미움받을 용기";
    ResultActions resultActions = this.mockMvc.perform(get("/book-search")
        .header("Authorization", authToken)
        .param("page", "0")
        .param("size", "5")
        .param("query", query)
        .accept(MediaType.APPLICATION_JSON));
    resultActions.andDo(print());

    // then
    resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$.content").exists())
        .andExpect(jsonPath("$.totalElements").exists())
        .andExpect(jsonPath("$.content[0].title").exists())
        .andExpect(jsonPath("$.content[0].thumbnail").exists())
        .andExpect(jsonPath("$.content[0].description").exists())
        .andExpect(jsonPath("$.content[0].isbn").exists())
        .andExpect(jsonPath("$.content[0].publisher").exists())
        .andExpect(jsonPath("$.content[0].publishedDate").exists())
        .andExpect(jsonPath("$.content[0].price").exists())
        .andExpect(jsonPath("$.content[0].salePrice").exists());
  }
}
