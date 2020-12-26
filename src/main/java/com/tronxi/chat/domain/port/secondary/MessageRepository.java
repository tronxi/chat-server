package com.tronxi.chat.domain.port.secondary;

import com.tronxi.chat.domain.model.Message;

import java.util.List;

public interface MessageRepository {
    void save(Message message);
    void save(List<Message> messageList);
}
