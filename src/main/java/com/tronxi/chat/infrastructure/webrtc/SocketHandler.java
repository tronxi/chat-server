package com.tronxi.chat.infrastructure.webrtc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.service.ConversationRetriever;
import com.tronxi.chat.infrastructure.webrtc.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SocketHandler extends TextWebSocketHandler {
    Map<String, WebSocketSession> sessions = new HashMap<>();

    private final ConversationRetriever conversationRetriever;

    @Autowired
    public SocketHandler(ConversationRetriever conversationRetriever) {
        this.conversationRetriever = conversationRetriever;
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        String userId = retrieveUserIdFromSession(session);
        Message message = null;
        try {
            message = new ObjectMapper().readValue(textMessage.getPayload(), Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Conversation conversation = conversationRetriever.findById(message.getConversationId());
        List<String> otherUsersId = retrieveOtherUsersInConversation(conversation, userId);

        otherUsersId.forEach(id -> {
            WebSocketSession websocketSession = sessions.get(id);
            if(websocketSession != null && websocketSession.isOpen()) {
                try {
                    websocketSession.sendMessage(textMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<String> retrieveOtherUsersInConversation(Conversation conversation, String userId) {
        return conversation.getUserList().stream()
                .map(User::getId)
                .filter(id -> !Objects.equals(id, userId))
                .collect(Collectors.toList());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = retrieveUserIdFromSession(session);
        sessions.put(userId, session);
    }

    private String retrieveUserIdFromSession(WebSocketSession session) {
        return getLastBitFromUrl(session.getUri().getPath());
    }

    private String getLastBitFromUrl(final String url){
        return url.replaceFirst(".*/([^/?]+).*", "$1");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(retrieveUserIdFromSession(session));
    }

}
