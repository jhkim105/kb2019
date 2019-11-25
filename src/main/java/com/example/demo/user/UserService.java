package com.example.demo.user;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCodes;
import com.example.demo.security.AuthUser;
import com.example.demo.security.JwtAuthenticationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtAuthenticationTokenService jwtAuthenticationTokenService;
  private final AuthenticationManager authenticationManager;

  @Transactional(readOnly = true)
  public LoginResponse login(LoginRequest loginRequest) {
    UsernamePasswordAuthenticationToken token = loginRequest.toUsernamePasswordAuthenticationToken();
    Authentication authentication;
    try {
      authentication = authenticationManager.authenticate(token);
    } catch(AuthenticationException ex) {
      throw new BusinessException(ErrorCodes.AUTHENTICATION_FAIL);
    }
    AuthUser authUser = (AuthUser) authentication.getPrincipal();

    String authToken = jwtAuthenticationTokenService.generateToken(authUser);
    return LoginResponse.builder().authToken(authToken).build();
  }

  public JoinResponse join(JoinRequest joinRequest) {
    User user = joinRequest.toUser(passwordEncoder);
    userRepository.save(user);
    return JoinResponse.from(user);
  }
}
