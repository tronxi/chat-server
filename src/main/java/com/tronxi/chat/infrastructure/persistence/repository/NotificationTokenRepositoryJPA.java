package com.tronxi.chat.infrastructure.persistence.repository;

import com.tronxi.chat.infrastructure.persistence.entity.NotificationTokenEntity;
import com.tronxi.chat.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTokenRepositoryJPA extends JpaRepository<NotificationTokenEntity, String> {
    Optional<NotificationTokenEntity> findByUserEntity(UserEntity userEntity);
}
