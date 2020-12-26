package com.tronxi.chat.domain.exception;

public class ConversationNotFoundException extends RuntimeException {
    public ConversationNotFoundException(String conversationId) {
        super("Conversation with Id: " + conversationId + " not found");
    }
}
