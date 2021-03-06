package com.example.demo.searchlog;

import com.example.demo.TestData;
import com.example.demo.base.IntegrationTests;
import com.example.demo.user.LoginRequest;
import com.example.demo.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SearchLogControllerTest extends IntegrationTests {

  @Autowired
  private UserService userService;

  @Test
  @Sql(scripts = "/search-log.sql",config = @SqlConfig(encoding = "UTF8"))
  void testMy() throws Exception {
    // given
    LoginRequest loginRequest = new LoginRequest(TestData.User.USERNAME, TestData.User.PASSWORD);
    String authToken = userService.login(loginRequest).getAuthToken();

    // when
    ResultActions resultActions =
        mockMvc.perform(
            get("/search-logs/my")
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "5")
            )
            .andDo(print());

    // then
    resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$.totalElements").value(11))
        .andExpect(jsonPath("$.content[0].keyword").value("first search"))
        .andExpect(jsonPath("$.content[0].createdDate").exists())

    ;
  }

  @Test
  @Sql(scripts = "/search-stats.sql", config = @SqlConfig(encoding = "UTF-8"))
  void testHotKeyword() throws Exception {
    // given
    LoginRequest loginRequest = new LoginRequest(TestData.User.USERNAME, TestData.User.PASSWORD);
    String authToken = userService.login(loginRequest).getAuthToken();

    // when
    ResultActions resultActions =
        mockMvc.perform(
            get("/search-logs/hot-keyword")
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print());

    // then
    resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$.totalElements").value(3))
        .andExpect(jsonPath("$.content[0].keyword").value("클린코드"))
        .andExpect(jsonPath("$.content[0].searchCount").value(12912))

    ;
  }



}