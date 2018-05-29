package com.ymmihw.springframework.webservlet.websocket.web.controller;

import java.security.Principal;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import com.google.gson.Gson;

@Controller
public class WebsocketSendToUserController {
  private Gson gson = new Gson();
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @MessageMapping("/message")
  @SendToUser("/queue/reply")
  public String processMessageFromClient(@Payload String message, Principal principal)
      throws Exception {
    String msg = gson.fromJson(message, Map.class).get("name").toString();
    logger.info("send message '{}' to user '{}'", msg, principal.getName());
    return msg;
  }

  @MessageExceptionHandler
  @SendToUser("/queue/errors")
  public String handleException(Throwable exception) {
    return exception.getMessage();
  }
}
