package com.tronxi.chat.infrastructure.api.rest.mapper;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.createconversation.CreateConversationOrder;
import com.tronxi.chat.infrastructure.api.rest.model.CreateConversationRequest;
import com.tronxi.chat.infrastructure.api.rest.model.CreateConversationResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateConversationMapper {
    public CreateConversationOrder toDomain(CreateConversationRequest createConversationRequest, String senderId) {
        return CreateConversationOrder.builder()
                .receiverId(createConversationRequest.getReceiverId())
                .senderId(senderId)
                .build();
    }

    public CreateConversationResponse toResponse(Conversation conversation) {
        return CreateConversationResponse.builder()
                .conversationId(conversation.getId())
                .build();
    }
}
