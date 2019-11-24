package com.example.demo.security;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCodes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
  private SecurityUtils() {
  }

  public static String getAuthUsername() {
    return getAuthUser().getUsername();
  }

  public static AuthUser getAuthUser() {
    SecurityContext ctx = SecurityContextHolder.getContext();
    Authentication authentication = ctx.getAuthentication();
    if (authentication == null)
      throw new BusinessException(ErrorCodes.AUTHENTICATION_NEED, "authentication not exists.");
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    if (userDetails == null) {
      throw new BusinessException(ErrorCodes.AUTHENTICATION_NEED, "userDetails not exists.");
    }
    return (AuthUser)userDetails;
  }

  public static AuthUser getAuthUserSilently() {
    try {
      return getAuthUser();
    } catch (Exception ex) {
      // ignored
    }
    return null;
  }
}
