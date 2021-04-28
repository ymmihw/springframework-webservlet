package com.ymmihw.spring.mvc.view.resolver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/sample.html");
  }

  // @Bean
  public ViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver bean = new InternalResourceViewResolver();
    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/view/");
    bean.setSuffix(".jsp");
    return bean;
  }

  // @Bean
  public ViewResolver resourceBundleViewResolver() {
    ResourceBundleViewResolver bean = new ResourceBundleViewResolver();
    bean.setBasename("views");
    return bean;
  }

  @Bean
  public ViewResolver xmlViewResolver() {
    XmlViewResolver bean = new XmlViewResolver();
    bean.setLocation(new ClassPathResource("views.xml"));
    return bean;
  }
}
