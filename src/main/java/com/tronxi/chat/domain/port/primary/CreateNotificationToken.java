package com.tronxi.chat.domain.port.primary;

import com.tronxi.chat.domain.model.notification.NotificationToken;

public interface CreateNotificationToken {
    void create(NotificationToken notificationToken);
}
