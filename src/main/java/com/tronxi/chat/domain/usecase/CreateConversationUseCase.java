package com.tronxi.chat.domain.usecase;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.createconversation.CreateConversationOrder;
import com.tronxi.chat.domain.port.primary.CreateConversation;
import com.tronxi.chat.domain.port.secondary.ConversationRepository;
import com.tronxi.chat.domain.service.UserRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateConversationUseCase implements CreateConversation {

    private final UserRetriever userRetriever;
    private final ConversationRepository conversationRepository;

    @Override
    public Conversation create(CreateConversationOrder createConversationOrder) {
        log.info("creating conversation with senderId {} and receiverId {} ", createConversationOrder.getSenderId(), createConversationOrder.getReceiverId());

        User senderUser = userRetriever.findById(createConversationOrder.getSenderId());
        User receiverUser = userRetriever.findById(createConversationOrder.getReceiverId());

        List<Conversation> senderConversationList = conversationRepository.findByUser(senderUser);
        return senderConversationList.stream()
                .filter(conversation -> conversation.containUser(receiverUser))
                .findFirst()
                .orElseGet(() -> createNewConversation(senderUser, receiverUser));
    }

    private Conversation createNewConversation(User senderUser, User receiverUser) {
        Conversation conversation = Conversation.createWithOutName();
        conversation.addUser(senderUser);
        conversation.addUser(receiverUser);
        conversationRepository.save(conversation);

        log.info("saved conversation with id {}", conversation.getId());
        return conversation;
    }
}
