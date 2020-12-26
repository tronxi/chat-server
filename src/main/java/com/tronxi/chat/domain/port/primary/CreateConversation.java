package com.tronxi.chat.domain.port.primary;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.createconversation.CreateConversationOrder;

public interface CreateConversation {
    Conversation create(CreateConversationOrder createConversationOrder);
}
