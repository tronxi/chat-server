package com.tronxi.chat.domain.port.secondary;

import com.tronxi.chat.domain.model.sendmessage.SendMessageOrder;

public interface NotificationEvent {
    void sendNotification(SendMessageOrder sendMessageOrder);
}
