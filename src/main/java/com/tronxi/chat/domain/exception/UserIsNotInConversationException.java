package com.tronxi.chat.domain.exception;

public class UserIsNotInConversationException extends RuntimeException {
    public UserIsNotInConversationException(String userId, String conversationId) {
        super("User with Id: " + userId + " is not in conversation with id: " + conversationId);
    }
}
