package com.tronxi.chat.domain.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String userId) {
        super("User with ID: " + userId + " not found");
    }
}
