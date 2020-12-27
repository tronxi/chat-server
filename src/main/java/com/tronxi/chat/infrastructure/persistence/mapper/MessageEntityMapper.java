package com.tronxi.chat.infrastructure.persistence.mapper;

import com.tronxi.chat.domain.model.Message;
import com.tronxi.chat.infrastructure.persistence.entity.MessageEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageEntityMapper {
    public MessageEntity toEntity(Message message) {
        return MessageEntity.builder()
                .id(message.getId())
                .message(message.getMessage())
                .readed(message.getRead())
                .senderId(message.getSenderId())
                .date(message.getDate())
                .build();
    }

    public List<MessageEntity> toEntity(List<Message> messageList) {
        return messageList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public Message toDomain(MessageEntity messageEntity) {
        return Message.builder()
                .id(messageEntity.getId())
                .message(messageEntity.getMessage())
                .read(messageEntity.getReaded())
                .senderId(messageEntity.getSenderId())
                .date(messageEntity.getDate())
                .build();
    }

    public List<Message> toDomain(List<MessageEntity> messageEntityList) {
        return messageEntityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
