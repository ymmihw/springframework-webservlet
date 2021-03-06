package com.ymmihw.springframework.web.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import com.ymmihw.springframework.web.exception.NotFoundException;
import com.ymmihw.springframework.web.handler.RestTemplateResponseErrorHandler;

@ContextConfiguration(classes = {NotFoundException.class, Bar.class})
@RestClientTest
public class RestTemplateResponseErrorHandlerIntegrationTest {

  @Autowired
  private MockRestServiceServer server;
  @Autowired
  private RestTemplateBuilder builder;

  @Test
  public void givenRemoteApiCall_when404Error_thenThrowNotFound() {
    assertNotNull(this.builder);
    assertNotNull(this.server);

    RestTemplate restTemplate =
        this.builder.errorHandler(new RestTemplateResponseErrorHandler()).build();

    this.server.expect(ExpectedCount.once(), requestTo("/bars/4242"))
        .andExpect(method(HttpMethod.GET)).andRespond(withStatus(HttpStatus.NOT_FOUND));

    assertThrows(NotFoundException.class, () -> {
      restTemplate.getForObject("/bars/4242", Bar.class);
      this.server.verify();
    });

  }
}
