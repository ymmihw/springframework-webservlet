package com.ymmihw.springframework;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,
    classes = {HttpRequestDemoConfig.class, ContentCachingFilter.class,
        PrintRequestContentFilter.class, PersonController.class})
@WebAppConfiguration
public class PersonControllerIntegrationTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private ContentCachingFilter contentCachingFilter;

  @Autowired
  private PrintRequestContentFilter printRequestContentFilter;

  @BeforeEach
  public void setup() throws Exception {

    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
        .addFilter(contentCachingFilter, "/**").addFilter(printRequestContentFilter, "/**").build();
  }

  @Test
  public void whenValidInput_thenCreateBook() throws IOException, Exception {
    // assign - given
    Person person = new Person("sumit", "abc", 100);

    // act - when
    ResultActions result = mockMvc.perform(post("/person").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(person)));

    // assert - then
    result.andExpect(status().isNoContent());
  }

}
