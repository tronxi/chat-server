package com.tronxi.chat.domain.model.createconversation;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateConversationOrder {
    String senderId;
    String receiverId;
}
