package com.tronxi.chat.configuration.websocket;

import com.tronxi.chat.infrastructure.webrtc.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebRTCConfiguration implements WebSocketConfigurer {

    private final SocketHandler socketHandler;

    @Autowired
    public WebRTCConfiguration(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(socketHandler, "/socket/{userId}")
                .setAllowedOrigins("*");
    }
}
