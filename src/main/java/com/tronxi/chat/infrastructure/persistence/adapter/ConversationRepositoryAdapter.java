package com.tronxi.chat.infrastructure.persistence.adapter;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.port.secondary.ConversationRepository;
import com.tronxi.chat.infrastructure.persistence.mapper.ConversationEntityMapper;
import com.tronxi.chat.infrastructure.persistence.repository.ConversationRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConversationRepositoryAdapter implements ConversationRepository {

    private final ConversationEntityMapper conversationEntityMapper;
    private final ConversationRepositoryJPA conversationRepositoryJPA;

    @Override
    public List<Conversation> findByUser(User user) {
        return conversationRepositoryJPA.findAll()
                .stream()
                .filter(conversationEntity -> conversationEntity.getUserEntityList().stream()
                    .anyMatch(userEntity -> userEntity.getId().equals(user.getId())))
                .map(conversationEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Conversation> findById(String conversationId) {
        return conversationRepositoryJPA.findById(conversationId)
                .map(conversationEntityMapper::toDomain);
    }

    @Override
    public void save(Conversation conversation) {
        conversationRepositoryJPA.save(conversationEntityMapper.toEntity(conversation));
    }
}
