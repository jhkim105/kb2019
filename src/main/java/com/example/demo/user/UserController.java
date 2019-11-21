package com.example.demo.user;

import com.example.demo.security.AuthUser;
import com.example.demo.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Api
public class UserController {

  private final UserService userService;

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    LoginResponse loginResponse = userService.login(loginRequest);
    return loginResponse;
  }

  @PostMapping("/join")
  public JoinResponse join(@RequestBody JoinRequest joinRequest) {
    return userService.join(joinRequest);
  }

  @GetMapping("/me")
  @ApiImplicitParam(name = "Authorization", value = "authToken", required = true, dataType = "string", paramType = "header")
  public AuthUser me() {
    AuthUser authUser = SecurityUtils.getAuthUser();
    return authUser;
  }



}
