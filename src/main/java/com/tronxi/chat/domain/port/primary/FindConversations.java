package com.tronxi.chat.domain.port.primary;

import com.tronxi.chat.domain.model.findconversation.FindConversationResult;

import java.util.List;

public interface FindConversations {
    List<FindConversationResult> findByUserId(String userId);
}
