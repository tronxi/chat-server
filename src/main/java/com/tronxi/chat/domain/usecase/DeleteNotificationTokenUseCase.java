package com.tronxi.chat.domain.usecase;

import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.port.primary.DeleteNotificationToken;
import com.tronxi.chat.domain.port.secondary.NotificationTokenRepository;
import com.tronxi.chat.domain.service.UserRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteNotificationTokenUseCase implements DeleteNotificationToken {

    private final NotificationTokenRepository notificationTokenRepository;
    private final UserRetriever userRetriever;

    @Override
    public void delete(String userId) {
        User user = userRetriever.findById(userId);
        notificationTokenRepository.deleteBy(user);
    }
}
