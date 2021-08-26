package com.tronxi.chat.infrastructure.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.sendmessage.SendMessageOrder;
import com.tronxi.chat.domain.port.secondary.NotificationTokenRepository;
import com.tronxi.chat.domain.service.ConversationRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PushNotificatorFirebase {

    private final ConversationRetriever conversationRetriever;
    private final NotificationTokenRepository notificationTokenRepository;

    public void sendNotification(SendMessageOrder sendMessageOrder) {
        Conversation conversation = conversationRetriever.findById(sendMessageOrder.getConversationId());
        List<String> otherUserId = retrieveOtherUsersInConversation(conversation, sendMessageOrder.getSenderId());
        String conversationName = retrieveName(conversation, sendMessageOrder.getSenderId());
        otherUserId.forEach(userId -> {
            notificationTokenRepository.findByUserId(userId)
                    .ifPresent(notificationToken -> send(conversationName, sendMessageOrder.getMessage(), notificationToken.getToken()));
        });
    }

    private String retrieveName(Conversation conversation, String userId) {
        User user = conversation.getUserList().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow();
        return user.getName();
    }

    private List<String> retrieveOtherUsersInConversation(Conversation conversation, String userId) {
        return conversation.getUserList().stream()
                .map(User::getId)
                .filter(id -> !userId.equals(id))
                .collect(Collectors.toList());
    }

    private void send(String title, String body, String token) {
        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(WebpushNotification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build())
                        .build())
                .setToken(token)
                .build();
        FirebaseMessaging.getInstance().sendAsync(message);
    }
}
