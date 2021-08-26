package com.tronxi.chat.infrastructure.api.rest.mapper;

import com.tronxi.chat.domain.model.notification.NotificationToken;
import com.tronxi.chat.infrastructure.api.rest.model.CreateNotificationTokenRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateNotificationMapper {

    public NotificationToken map(String userId, CreateNotificationTokenRequest createNotificationTokenRequest) {
        return NotificationToken.builder()
                .userId(userId)
                .token(createNotificationTokenRequest.getToken())
                .build();
    }
}
