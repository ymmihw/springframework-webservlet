package com.ymmihw.springframework.webservlet.websocket.web.config;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketSendToUserConfig implements WebSocketMessageBrokerConfigurer {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic/", "/queue/");
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    HandshakeInterceptor handshakeInterceptor = new HandshakeInterceptor() {

      @Override
      public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
          WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
          ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
          HttpSession session = servletRequest.getServletRequest().getSession();
          WebSocketSendToUserConfig.this.logger.info("sessionId is {}", session.getId());
          attributes.put("sessionId", session.getId());
        }
        return true;
      }

      @Override
      public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
          WebSocketHandler wsHandler, Exception exception) {}
    };
    registry.addEndpoint("/greeting").addInterceptors(handshakeInterceptor).withSockJS();
  }
}
