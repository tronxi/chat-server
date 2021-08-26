package com.tronxi.chat.infrastructure.api.rest.controller;

import com.tronxi.chat.domain.model.notification.NotificationToken;
import com.tronxi.chat.domain.port.primary.CreateNotificationToken;
import com.tronxi.chat.domain.port.primary.DeleteNotificationToken;
import com.tronxi.chat.infrastructure.api.rest.mapper.CreateNotificationMapper;
import com.tronxi.chat.infrastructure.api.rest.model.CreateNotificationTokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notifications/token")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("authenticated")
public class NotificationController {

    private final CreateNotificationMapper createNotificationMapper;
    private final CreateNotificationToken createNotificationToken;
    private final DeleteNotificationToken deleteNotificationToken;

    @PutMapping("/{id}")
    public ResponseEntity<Void> setNotificationToken(@PathVariable String id,
                                                     @RequestBody CreateNotificationTokenRequest createNotificationTokenRequest) {
        NotificationToken notificationToken = createNotificationMapper.map(id, createNotificationTokenRequest);
        createNotificationToken.create(notificationToken);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationToken(@PathVariable String id) {
        deleteNotificationToken.delete(id);
        return ResponseEntity.ok().build();
    }
}
