package com.ymmihw.spring.mvc.webcontexts.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.ymmihw.spring.mvc.webcontexts.service"})
public class RootApplicationConfig {
}
