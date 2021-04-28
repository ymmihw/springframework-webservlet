package com.ymmihw.spring.mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is the test class for {@link org.baeldung.controller.controller.PassParametersController}
 * class. 09/09/2017
 *
 * @author Ahmet Cetin
 */
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = {Application.class})
public class PassParametersControllerIntegrationTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @BeforeEach
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void testPassParametersWithModel() throws Exception {
    ModelAndView mv = this.mockMvc.perform(MockMvcRequestBuilders.get("/showViewPage")).andReturn()
        .getModelAndView();

    // Validate view
    assertEquals(mv.getViewName(), "viewPage");

    // Validate attribute
    assertEquals(mv.getModelMap().get("message").toString(), "Baeldung");
    assertEquals(mv.getModelMap().get("spring").toString(), "mvc");
  }

  @Test
  public void testPassParametersWithModelMap() throws Exception {
    ModelAndView mv = this.mockMvc.perform(MockMvcRequestBuilders.get("/printViewPage")).andReturn()
        .getModelAndView();

    // Validate view
    assertEquals(mv.getViewName(), "viewPage");

    // Validate attribute
    assertEquals(mv.getModelMap().get("message").toString(), "Baeldung");
    assertEquals(mv.getModelMap().get("spring").toString(), "mvc");
  }

  @Test
  public void testPassParametersWithModelAndView() throws Exception {
    ModelAndView mv = this.mockMvc.perform(MockMvcRequestBuilders.get("/goToViewPage")).andReturn()
        .getModelAndView();

    // Validate view
    assertEquals(mv.getViewName(), "viewPage");

    // Validate attribute
    assertEquals(mv.getModelMap().get("message").toString(), "Baeldung");
  }
}
