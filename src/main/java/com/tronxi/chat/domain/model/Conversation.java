package com.tronxi.chat.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
@Builder(toBuilder = true)
public class Conversation {
    String id;
    String name;
    List<User> userList;
    List<Message> messageList;

    public boolean containUser(User user) {
        return this.userList.stream()
                .anyMatch(u -> u.equals(user));
    }

    public Long countUnreadMessages(String receiverId) {
        return messageList.stream()
                .filter(message -> !message.getRead())
                .filter(message -> !message.getSenderId().equals(receiverId))
                .count();
    }

    public Conversation markAsRead(String userId) {
        List<Message> readMessages = messageList.stream()
                .map(message -> message.markAsRead(userId))
                .collect(Collectors.toList());
        return this.toBuilder()
                .messageList(readMessages)
                .build();
    }

    public void addUser(User user) {
        this.userList.add(user);
    }

    public void addMessage(Message message) {
        this.messageList.add(message);
    }

    public static Conversation createWithOutName() {
        return Conversation.builder()
                .name("defaultName")
                .userList(new ArrayList<>())
                .messageList(new ArrayList<>())
                .id(UUID.randomUUID().toString())
                .build();
    }
}
