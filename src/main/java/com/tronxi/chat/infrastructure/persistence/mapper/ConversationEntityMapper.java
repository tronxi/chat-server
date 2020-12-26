package com.tronxi.chat.infrastructure.persistence.mapper;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.infrastructure.persistence.entity.ConversationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ConversationEntityMapper {

    private final UserEntityMapper userEntityMapper;
    private final MessageEntityMapper messageEntityMapper;

    public Conversation toDomain(ConversationEntity conversationEntity) {
        return Conversation.builder()
                .id(conversationEntity.getId())
                .name(conversationEntity.getName())
                .messageList(Collections.emptyList())
                .messageList(messageEntityMapper.toDomain(conversationEntity.getMessageEntityList()))
                .userList(userEntityMapper.toDomain(conversationEntity.getUserEntityList()))
                .build();
    }

    public ConversationEntity toEntity(Conversation conversation) {
        return ConversationEntity.builder()
                .id(conversation.getId())
                .name(conversation.getName())
                .messageEntityList(messageEntityMapper.toEntity(conversation.getMessageList()))
                .userEntityList(userEntityMapper.toEntity(conversation.getUserList()))
                .build();
    }
}
