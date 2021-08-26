package com.tronxi.chat.domain.usecase;

import com.tronxi.chat.domain.exception.UserIsNotInConversationException;
import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.model.Message;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.sendmessage.SendMessageOrder;
import com.tronxi.chat.domain.port.primary.SendMessage;
import com.tronxi.chat.domain.port.secondary.ConversationRepository;
import com.tronxi.chat.domain.port.secondary.MessageEvent;
import com.tronxi.chat.domain.port.secondary.MessageRepository;
import com.tronxi.chat.domain.service.ConversationRetriever;
import com.tronxi.chat.domain.service.UserRetriever;
import com.tronxi.chat.infrastructure.firebase.PushNotificatorFirebase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendMessageUseCase implements SendMessage {

    private final UserRetriever userRetriever;
    private final ConversationRetriever conversationRetriever;

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final MessageEvent messageEvent;
    private final PushNotificatorFirebase pushNotificatorFirebase;

    @Override
    public void send(SendMessageOrder sendMessageOrder) {
        log.info("sending message to conversationId {} from userId {}", sendMessageOrder.getConversationId(), sendMessageOrder.getSenderId());
        User user = userRetriever.findById(sendMessageOrder.getSenderId());
        Conversation conversation = conversationRetriever.findById(sendMessageOrder.getConversationId());

        if(!checkUserInConversation(conversation, user)) {
            throw new UserIsNotInConversationException(user.getId(), conversation.getId());
        }

        Message message = Message.create(sendMessageOrder.getMessage(),sendMessageOrder.getSenderId());
        messageRepository.save(message);
        conversation.addMessage(message);

        conversationRepository.save(conversation);
        messageEvent.send(conversation);
        pushNotificatorFirebase.sendNotification(sendMessageOrder);
    }

    private boolean checkUserInConversation(Conversation conversation, User user) {
        return conversation.getUserList().stream()
                .anyMatch(u -> u.equals(user));
    }
}
