package com.ymmihw.springframework.webservlet.websocket.config;

import static com.ymmihw.springframework.webservlet.websocket.Constants.SECURED_CHAT;
import static com.ymmihw.springframework.webservlet.websocket.Constants.SECURED_CHAT_HISTORY;
import static com.ymmihw.springframework.webservlet.websocket.Constants.SECURED_CHAT_ROOM;
import static com.ymmihw.springframework.webservlet.websocket.Constants.SECURED_CHAT_SPECIFIC_USER;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker(SECURED_CHAT_HISTORY, SECURED_CHAT_SPECIFIC_USER);
    config.setApplicationDestinationPrefixes("/app");
    config.setUserDestinationPrefix("/secured/user");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint(SECURED_CHAT_ROOM).withSockJS();
    registry.addEndpoint(SECURED_CHAT).withSockJS();
  }
}

