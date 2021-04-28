package com.ymmihw.spring.mvc.webcontexts.service;

import org.springframework.stereotype.Service;

@Service
public class GreeterService {

  public String greet() {
    return "greeting";
  }

}
