package com.tronxi.chat.domain.port.primary;

import com.tronxi.chat.domain.model.sendmessage.SendMessageOrder;

public interface SendMessage {
    void send(SendMessageOrder sendMessageOrder);
}
