package com.tronxi.chat.infrastructure.persistence.repository;

import com.tronxi.chat.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByName(String name);
}
