package com.tronxi.chat.domain.port.primary;

import com.tronxi.chat.domain.model.readmessage.ReadMessageOrder;
import com.tronxi.chat.domain.model.readmessage.ReadMessageResult;

import java.util.List;

public interface ReadMessage {
    List<ReadMessageResult> read(ReadMessageOrder readMessageRequest);
}
