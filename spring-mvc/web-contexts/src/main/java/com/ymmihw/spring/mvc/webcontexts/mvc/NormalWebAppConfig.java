package com.ymmihw.spring.mvc.webcontexts.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ymmihw.spring.mvc.webcontexts.normal"})
public class NormalWebAppConfig implements WebMvcConfigurer {
}
