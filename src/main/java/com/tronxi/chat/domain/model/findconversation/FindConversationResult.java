package com.tronxi.chat.domain.model.findconversation;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FindConversationResult {
    String conversationId;
    String name;
    Long unreadMessages;
}
