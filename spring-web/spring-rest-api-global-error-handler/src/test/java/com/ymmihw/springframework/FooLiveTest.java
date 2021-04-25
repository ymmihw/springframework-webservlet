package com.ymmihw.springframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import com.ymmihw.springframework.web.ApiError;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FooLiveTest {
  private static final String URL_PREFIX = "http://localhost:";

  @LocalServerPort
  private int localServerPort;

  @Test
  public void whenTry_thenOK() {
    final Response response = RestAssured.given().get(URL_PREFIX + localServerPort + "/foos");
    assertEquals(200, response.statusCode());
    System.out.println(response.asString());
  }

  @Test
  public void whenMethodArgumentMismatch_thenBadRequest() {
    final Response response = RestAssured.given().get(URL_PREFIX + localServerPort + "/foos/ccc");
    final ApiError error = response.as(ApiError.class);
    assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
    assertEquals(1, error.getErrors().size());
    assertTrue(error.getErrors().get(0).contains("should be of type"));
  }

  @Test
  public void whenNoHandlerForHttpRequest_thenNotFound() {
    final Response response = RestAssured.given().delete(URL_PREFIX + localServerPort + "/xx");
//    final ApiError error = response.as(ApiError.class);
//    assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
//    assertEquals(1, error.getErrors().size());
//    assertTrue(error.getErrors().get(0).contains("No handler found"));
    System.out.println(response.asString());

  }

  @Test
  public void whenHttpRequestMethodNotSupported_thenMethodNotAllowed() {
    final Response response = RestAssured.given().delete(URL_PREFIX + localServerPort + "/foos/1");
    final ApiError error = response.as(ApiError.class);
    assertEquals(HttpStatus.METHOD_NOT_ALLOWED, error.getStatus());
    assertEquals(1, error.getErrors().size());
    assertTrue(error.getErrors().get(0).contains("Supported methods are"));
    System.out.println(response.asString());

  }

  @Test
  public void whenSendInvalidHttpMediaType_thenUnsupportedMediaType() {
    final Response response =
        RestAssured.given().body("").post(URL_PREFIX + localServerPort + "/foos");
    final ApiError error = response.as(ApiError.class);
    assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, error.getStatus());
    assertEquals(1, error.getErrors().size());
    assertTrue(error.getErrors().get(0).contains("media type is not supported"));
    System.out.println(response.asString());

  }

}
