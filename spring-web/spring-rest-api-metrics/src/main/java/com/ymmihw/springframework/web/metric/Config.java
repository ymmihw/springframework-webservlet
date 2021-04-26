package com.ymmihw.springframework.web.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
  @Bean
  public FilterRegistrationBean<?> mvcLoggingFilterRegistrationBean(
      @Autowired MetricFilter metricFilter) {
    FilterRegistrationBean<MetricFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(metricFilter);
    registrationBean.setOrder(1);
    return registrationBean;
  }
}
