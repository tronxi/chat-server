package com.tronxi.chat.infrastructure.persistence.repository;

import com.tronxi.chat.infrastructure.persistence.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepositoryJPA extends JpaRepository<MessageEntity, String> {
}
