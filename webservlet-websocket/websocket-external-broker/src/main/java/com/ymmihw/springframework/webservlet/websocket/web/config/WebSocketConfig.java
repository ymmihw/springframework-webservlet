package com.ymmihw.springframework.webservlet.websocket.web.config;

import java.net.InetSocketAddress;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompReactorNettyCodec;
import org.springframework.messaging.tcp.reactor.ReactorNettyTcpClient;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(final MessageBrokerRegistry config) {
    config.enableStompBrokerRelay("/topic").setTcpClient(createTcpClient());
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry.addEndpoint("/chat");
    registry.addEndpoint("/chat").withSockJS();
  }

  private ReactorNettyTcpClient<byte[]> createTcpClient() {
    return new ReactorNettyTcpClient<>(
        client -> client
            .remoteAddress(() -> InetSocketAddress.createUnresolved("172.16.10.177", 61613)),
        new StompReactorNettyCodec());
  }
}
