package com.tronxi.chat.domain.model.notification;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NotificationToken {
    String userId;
    String token;
}
