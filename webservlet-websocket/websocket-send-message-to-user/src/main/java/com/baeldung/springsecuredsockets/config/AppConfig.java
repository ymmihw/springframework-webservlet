package com.baeldung.springsecuredsockets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/secured/socket").setViewName("socket");
    registry.addViewController("/secured/success").setViewName("success");
    registry.addViewController("/denied").setViewName("denied");
  }

  @Bean
  public UrlBasedViewResolver viewResolver() {
    final UrlBasedViewResolver bean = new UrlBasedViewResolver();
    bean.setPrefix("/WEB-INF/jsp/");
    bean.setSuffix(".jsp");
    bean.setViewClass(JstlView.class);
    return bean;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/")
        .setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
  }

}
