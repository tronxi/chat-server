package com.tronxi.chat.domain.model.sendmessage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SendMessageOrder {
    String senderId;
    String conversationId;
    String message;
}
