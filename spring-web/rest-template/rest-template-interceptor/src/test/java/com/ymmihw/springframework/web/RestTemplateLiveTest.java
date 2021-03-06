package com.ymmihw.springframework.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import com.ymmihw.springframework.web.config.RestClientConfig;
import com.ymmihw.springframework.web.model.LoginForm;

@SpringBootTest
@ContextConfiguration(classes = RestClientConfig.class)
public class RestTemplateLiveTest {

  @Autowired
  RestTemplate restTemplate;

  @Test
  public void givenRestTemplate_whenRequested_thenLogAndModifyResponse() {
    LoginForm loginForm = new LoginForm("userName", "password");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<LoginForm> requestEntity = new HttpEntity<LoginForm>(loginForm, headers);

    ResponseEntity<String> responseEntity =
        restTemplate.postForEntity("http://httpbin.org/post", requestEntity, String.class);

    assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
    assertThat(responseEntity.getHeaders().get("Foo").get(0), is(equalTo("bar")));
  }
}
