package com.tronxi.chat.domain.port.secondary;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository {
    List<Conversation> findByUser(User user);
    Optional<Conversation> findById(String conversationId);
    void save(Conversation conversation);
}
