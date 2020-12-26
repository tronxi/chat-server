package com.tronxi.chat.infrastructure.persistence.adapter;

import com.tronxi.chat.domain.model.Message;
import com.tronxi.chat.domain.port.secondary.MessageRepository;
import com.tronxi.chat.infrastructure.persistence.mapper.MessageEntityMapper;
import com.tronxi.chat.infrastructure.persistence.repository.MessageRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageRepositoryAdapter implements MessageRepository {

    private final MessageEntityMapper messageEntityMapper;
    private final MessageRepositoryJPA messageRepositoryJPA;

    @Override
    public void save(Message message) {
        messageRepositoryJPA.save(messageEntityMapper.toEntity(message));
    }

    @Override
    public void save(List<Message> messageList) {
        messageList.forEach(this::save);
    }
}
