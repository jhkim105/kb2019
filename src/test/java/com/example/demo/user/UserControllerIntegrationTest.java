package com.example.demo.user;

import com.example.demo.base.IntegrationTests;
import com.example.demo.common.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerIntegrationTest extends IntegrationTests{

  @Autowired
  private UserService userService;


  @Test
  public void testJoin() throws Exception {
    // when
    JoinRequest joinRequest = new JoinRequest();
    joinRequest.setUsername("newuser");
    joinRequest.setPassword("222222");
    joinRequest.setConfirmPassword("222222");
    ResultActions resultActions =
        mockMvc.perform(post("/users/join")
            .content(JsonUtils.toString(joinRequest))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());


    // then
    resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value(joinRequest.getUsername()));
  }

  @Test
  public void testLogin() throws Exception {
    // when
    LoginRequest loginRequest = new LoginRequest("user", "111111");
    ResultActions resultActions =
        mockMvc.perform(post("/users/login")
            .content(JsonUtils.toString(loginRequest))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());


    // then
    resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$.authToken").exists());
  }

  @Test
  public void testMe() throws Exception {
    // given
    LoginRequest loginRequest = new LoginRequest("user", "111111");
    String authToken = userService.login(loginRequest).getAuthToken();

    // when
    ResultActions resultActions =
        mockMvc.perform(
            get("/users/me")
                .header(HttpHeaders.AUTHORIZATION, authToken)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

    // then
    resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.username").exists())
        .andExpect(jsonPath("$.authority").exists());

  }


}