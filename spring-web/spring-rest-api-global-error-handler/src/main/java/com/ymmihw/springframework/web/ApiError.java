package com.ymmihw.springframework.web;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {
  private HttpStatus status;
  private String message;
  private List<String> errors;
  private String path;
  private Timestamp timestamp;

  public ApiError() {
    super();
  }

  public ApiError(final HttpStatus status, final String message, final List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public ApiError(final HttpStatus status, final String message, final String error) {
    super();
    this.status = status;
    this.message = message;
    this.errors = Arrays.asList(error);
  }
}
