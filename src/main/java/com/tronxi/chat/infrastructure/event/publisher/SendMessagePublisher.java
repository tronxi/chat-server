package com.tronxi.chat.infrastructure.event.publisher;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.port.secondary.MessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendMessagePublisher implements MessageEvent {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void send(Conversation conversation) {
        conversation.getUserList().forEach(user -> simpMessagingTemplate.convertAndSend("/topic/" + user.getId(), "SEND"));
        simpMessagingTemplate.convertAndSend("/topic/" + conversation.getId(),"SEND" );
    }
}
