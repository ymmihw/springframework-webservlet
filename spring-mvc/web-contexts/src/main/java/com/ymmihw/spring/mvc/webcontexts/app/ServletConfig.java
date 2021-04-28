package com.ymmihw.spring.mvc.webcontexts.app;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import com.ymmihw.spring.mvc.webcontexts.mvc.NormalWebAppConfig;
import com.ymmihw.spring.mvc.webcontexts.mvc.SecureWebAppConfig;

@Configuration
public class ServletConfig {

  @Bean
  public ServletRegistrationBean<?> normalServlet() {
    AnnotationConfigWebApplicationContext applicationContext =
        new AnnotationConfigWebApplicationContext();
    applicationContext.register(NormalWebAppConfig.class);
    DispatcherServlet servlet = new DispatcherServlet(applicationContext);

    ServletRegistrationBean<?> registrationBean = new ServletRegistrationBean<>(servlet);
    registrationBean.addUrlMappings("/api/*");
    registrationBean.setName("normal-dispatcher");
    return registrationBean;
  }


  @Bean
  public ServletRegistrationBean<?> secureServlet() {
    AnnotationConfigWebApplicationContext applicationContext =
        new AnnotationConfigWebApplicationContext();
    applicationContext.register(SecureWebAppConfig.class);
    DispatcherServlet servlet = new DispatcherServlet(applicationContext);

    ServletRegistrationBean<?> registrationBean = new ServletRegistrationBean<>(servlet);
    registrationBean.addUrlMappings("/s/api/*");
    registrationBean.setName("secure-dispatcher");
    return registrationBean;
  }
}
