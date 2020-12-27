package com.tronxi.chat.domain.usecase;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.findconversation.FindConversationResult;
import com.tronxi.chat.domain.port.primary.FindConversations;
import com.tronxi.chat.domain.port.secondary.ConversationRepository;
import com.tronxi.chat.domain.service.UserRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindConversationsUseCase implements FindConversations {

    private final UserRetriever userRetriever;
    private final ConversationRepository conversationRepository;

    @Override
    public List<FindConversationResult> findByUserId(String userId) {
        log.info("find conversations by userId: {}", userId);
        User user = userRetriever.findById(userId);
        List<Conversation> conversationList = conversationRepository.findByUser(user).stream()
                .filter(conversation -> !conversation.getMessageList().isEmpty())
                .collect(Collectors.toList());
        return conversationList.stream()
                .map(conversation -> generateName(conversation, userId))
                .collect(Collectors.toList());
    }

    private FindConversationResult generateName(Conversation conversation, String userId) {
        User user = conversation.getUserList().stream()
                .filter(u -> !u.getId().equals(userId))
                .findFirst()
                .orElseThrow();
        return FindConversationResult.builder()
                .conversationId(conversation.getId())
                .name(user.getName())
                .unreadMessages(conversation.countUnreadMessages(userId))
                .build();
    }
}
