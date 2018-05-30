package com.ymmihw.springframework.webservlet.websocket.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.ymmihw.springframework.webservlet.websocket.model.Message;
import com.ymmihw.springframework.webservlet.websocket.model.OutputMessage;

@Controller
public class ChatController {
  @MessageMapping("/chat")
  @SendTo("/secured/topic/messages")
  public OutputMessage send(final Message message) throws Exception {
    final String time = new SimpleDateFormat("HH:mm").format(new Date());
    return new OutputMessage(message.getFrom(), message.getText(), time);
  }
}
