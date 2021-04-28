package com.ymmihw.spring.mvc.webcontexts.normal;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import com.ymmihw.spring.mvc.webcontexts.service.ApplicationContextUtilService;
import com.ymmihw.spring.mvc.webcontexts.service.GreeterService;

@RestController
public class HelloWorldController {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private GreeterService greeterService;

  @Autowired
  @Qualifier("contextAware")
  private ApplicationContextUtilService contextUtilService;

  private void processContext() {
    ApplicationContext context = contextUtilService.getApplicationContext();
    System.out.println("application context : " + context);
    System.out
        .println("application context Beans: " + Arrays.asList(context.getBeanDefinitionNames()));
    System.out.println("context : " + webApplicationContext);
    System.out
        .println("context Beans: " + Arrays.asList(webApplicationContext.getBeanDefinitionNames()));
  }

  @RequestMapping(path = "/welcome")
  public String helloWorld() {
    processContext();
    return "Normal " + greeterService.greet();
  }
}
