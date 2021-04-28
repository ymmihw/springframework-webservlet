package com.ymmihw.spring.mvc.session.attributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class TodoControllerWithScopedProxyTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenSubmit_thenSubsequentFormRequestContainsMostRecentTodo() throws Exception {
    mockMvc.perform(post("/scopedproxy/form").param("description", "newtodo"))
        .andExpect(status().is3xxRedirection()).andReturn();

    MvcResult result = mockMvc.perform(get("/scopedproxy/form")).andExpect(status().isOk())
        .andExpect(model().attributeExists("todo")).andReturn();
    TodoItem item = (TodoItem) result.getModelAndView().getModel().get("todo");
    assertEquals("newtodo", item.getDescription());
  }

  @Test
  @Disabled("failed when run together with whenSubmit_thenSubsequentFormRequestContainsMostRecentTodo")
  public void whenFirstRequest_thenContainsUnintializedTodo() throws Exception {
    MvcResult result = mockMvc.perform(get("/scopedproxy/form")).andExpect(status().isOk())
        .andExpect(model().attributeExists("todo")).andReturn();

    TodoItem item = (TodoItem) result.getModelAndView().getModel().get("todo");
    assertNull(item.getDescription());
  }

}
