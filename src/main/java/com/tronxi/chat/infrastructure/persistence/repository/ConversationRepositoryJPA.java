package com.tronxi.chat.infrastructure.persistence.repository;

import com.tronxi.chat.infrastructure.persistence.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepositoryJPA extends JpaRepository<ConversationEntity, String> {

}
