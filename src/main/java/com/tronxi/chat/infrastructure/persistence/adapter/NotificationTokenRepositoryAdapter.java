package com.tronxi.chat.infrastructure.persistence.adapter;

import com.tronxi.chat.domain.exception.UserNotFound;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.notification.NotificationToken;
import com.tronxi.chat.domain.port.secondary.NotificationTokenRepository;
import com.tronxi.chat.infrastructure.persistence.entity.NotificationTokenEntity;
import com.tronxi.chat.infrastructure.persistence.entity.UserEntity;
import com.tronxi.chat.infrastructure.persistence.mapper.NotificationTokenEntityMapper;
import com.tronxi.chat.infrastructure.persistence.repository.NotificationTokenRepositoryJPA;
import com.tronxi.chat.infrastructure.persistence.repository.UserRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NotificationTokenRepositoryAdapter implements NotificationTokenRepository {

    private final UserRepositoryJPA userRepositoryJPA;
    private final NotificationTokenEntityMapper notificationTokenEntityMapper;
    private final NotificationTokenRepositoryJPA notificationTokenRepositoryJPA;

    @Override
    public void save(NotificationToken notificationToken) {
        UserEntity userEntity = userRepositoryJPA.findById(notificationToken.getUserId())
                .orElseThrow(() -> new UserNotFound(notificationToken.getUserId()));

        NotificationTokenEntity notificationTokenEntity = notificationTokenRepositoryJPA.findByUserEntity(userEntity)
                .orElse(notificationTokenEntityMapper.map(notificationToken, userEntity));
        notificationTokenEntity.setToken(notificationToken.getToken());
        notificationTokenRepositoryJPA.save(notificationTokenEntity);
    }

    @Override
    public void deleteBy(User user) {
        UserEntity userEntity = userRepositoryJPA.findById(user.getId())
                .orElseThrow(() -> new UserNotFound(user.getId()));
        notificationTokenRepositoryJPA.findByUserEntity(userEntity)
                .ifPresent(notificationTokenRepositoryJPA::delete);
    }

    @Override
    public Optional<NotificationToken> findByUserId(String userId) {
        UserEntity userEntity = userRepositoryJPA.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));
        return notificationTokenRepositoryJPA.findByUserEntity(userEntity)
                .map(notificationTokenEntityMapper::map);
    }
}
