package com.tronxi.chat.domain.port.secondary;

import com.tronxi.chat.domain.model.Conversation;

public interface MessageEvent {
    void send(Conversation conversation);
}
