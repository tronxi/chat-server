package com.tronxi.chat.domain.service;

import com.tronxi.chat.domain.exception.ConversationNotFoundException;
import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.port.secondary.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConversationRetriever {

    private final ConversationRepository conversationRepository;

    public Conversation findById(String conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ConversationNotFoundException(conversationId));
    }
}
