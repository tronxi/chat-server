package com.tronxi.chat.domain.model.readmessage;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class ReadMessageResult {
    String message;
    Date date;
    Boolean isMyMessage;
}
