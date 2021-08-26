package com.tronxi.chat.domain.usecase;

import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.notification.NotificationToken;
import com.tronxi.chat.domain.port.primary.CreateNotificationToken;
import com.tronxi.chat.domain.port.secondary.NotificationTokenRepository;
import com.tronxi.chat.domain.service.UserRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateNotificationTokenUseCase implements CreateNotificationToken {

    private final NotificationTokenRepository notificationTokenRepository;
    private final UserRetriever userRetriever;

    @Override
    public void create(NotificationToken notificationToken) {
        User user = userRetriever.findById(notificationToken.getUserId());
        notificationTokenRepository.save(notificationToken);
    }
}
