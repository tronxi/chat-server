package com.tronxi.chat.infrastructure.persistence.mapper;

import com.tronxi.chat.domain.model.notification.NotificationToken;
import com.tronxi.chat.infrastructure.persistence.entity.NotificationTokenEntity;
import com.tronxi.chat.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationTokenEntityMapper {

    public NotificationTokenEntity map(NotificationToken notificationToken, UserEntity userEntity) {
        return NotificationTokenEntity.builder()
                .id(UUID.randomUUID().toString())
                .userEntity(userEntity)
                .token(notificationToken.getToken())
                .build();
    }

    public NotificationToken map(NotificationTokenEntity notificationTokenEntity) {
        return NotificationToken.builder()
                .token(notificationTokenEntity.getToken())
                .userId(notificationTokenEntity.getUserEntity().getId())
                .build();
    }
}
