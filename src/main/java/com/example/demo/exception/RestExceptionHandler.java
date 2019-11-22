package com.example.demo.exception;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

// TODO: logging
@ControllerAdvice(annotations = { Api.class })
public class RestExceptionHandler {

  @Autowired private ErrorAttributes errorAttributes;

  @ExceptionHandler({ BusinessException.class })
  protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
    return handleException(ex.getErrorCodes(), ex.getMessage(), request);
  }

  @ExceptionHandler({ Exception.class })
  protected ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
    return handleException(ErrorCodes.SYSTEM_ERROR, "", request);
  }

  protected ResponseEntity<Object> handleException(ErrorCodes errorCodes, String message, WebRequest request) {
    HttpStatus status = errorCodes.getStatus();
    request.setAttribute("javax.servlet.error.status_code", status.value(), WebRequest.SCOPE_REQUEST);
    Map<String, Object> errorAttributeMap = errorAttributes.getErrorAttributes(request, false);
    errorAttributeMap.put("code", errorCodes.getCode());
    errorAttributeMap.put("message", message);
    return new ResponseEntity<>(errorAttributeMap, new HttpHeaders(), status);
  }
}
