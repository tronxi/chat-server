package com.tronxi.chat.domain.model.readmessage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReadMessageOrder {
    String conversationId;
    String userId;
}
