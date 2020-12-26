package com.tronxi.chat.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Message {
    String id;
    String message;
    Boolean read;
    String senderId;
    Date date;

    public static Message create(String message, String senderId) {
        return Message.builder()
                .id(UUID.randomUUID().toString())
                .message(message)
                .read(false)
                .senderId(senderId)
                .date(new Date())
                .build();
    }

    public Message markAsRead(String userId) {
        if(this.getSenderId().equals(userId)) return this;

        return this.toBuilder()
                .read(true)
                .build();
    }


}
