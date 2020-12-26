package com.tronxi.chat.domain.usecase;

import com.tronxi.chat.domain.exception.UserIsNotInConversationException;
import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.Message;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.readmessage.ReadMessageOrder;
import com.tronxi.chat.domain.model.readmessage.ReadMessageResult;
import com.tronxi.chat.domain.port.primary.ReadMessage;
import com.tronxi.chat.domain.port.secondary.MessageRepository;
import com.tronxi.chat.domain.service.ConversationRetriever;
import com.tronxi.chat.domain.service.UserRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReadMessageUseCase implements ReadMessage {

    private final UserRetriever userRetriever;
    private final ConversationRetriever conversationRetriever;
    private final MessageRepository messageRepository;

    @Override
    public List<ReadMessageResult> read(ReadMessageOrder readMessageRequest) {
        log.info("reading message from conversationId {} with userId {}", readMessageRequest.getConversationId(), readMessageRequest.getUserId());

        User user = userRetriever.findById(readMessageRequest.getUserId());
        Conversation conversation = conversationRetriever.findById(readMessageRequest.getConversationId());

        if(!checkUserInConversation(conversation, user)) {
            throw new UserIsNotInConversationException(user.getId(), conversation.getId());
        }
        Conversation readConversation = conversation.markAsRead(user.getId());
        messageRepository.save(readConversation.getMessageList());

        return readConversation.getMessageList().stream()
                .map(message -> mapToResult(message, user.getId()))
                .collect(Collectors.toList());
    }

    private ReadMessageResult mapToResult(Message message, String userId) {
        return ReadMessageResult.builder()
                .message(message.getMessage())
                .isMyMessage(message.getSenderId().equals(userId))
                .date(message.getDate())
                .build();
    }

    private boolean checkUserInConversation(Conversation conversation, User user) {
        return conversation.getUserList().stream()
                .anyMatch(u -> u.equals(user));
    }
}
