package com.tronxi.chat.domain.port.secondary;

import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.notification.NotificationToken;

import java.util.Optional;

public interface NotificationTokenRepository {
    void save(NotificationToken notificationToken);
    void deleteBy(User user);
    Optional<NotificationToken> findByUserId(String userId);
}
