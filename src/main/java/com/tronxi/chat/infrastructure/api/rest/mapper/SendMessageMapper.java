package com.tronxi.chat.infrastructure.api.rest.mapper;

import com.tronxi.chat.domain.model.sendmessage.SendMessageOrder;
import com.tronxi.chat.infrastructure.api.rest.model.SendMessageRequest;
import org.springframework.stereotype.Component;

@Component
public class SendMessageMapper {
    public SendMessageOrder toDomain(SendMessageRequest sendMessageRequest, String conversationId, String senderId) {
        return SendMessageOrder.builder()
                .conversationId(conversationId)
                .senderId(senderId)
                .message(sendMessageRequest.getMessage())
                .build();
    }
}
