package com.tronxi.chat.infrastructure.api.rest.mapper;

import com.tronxi.chat.domain.model.readmessage.ReadMessageOrder;
import com.tronxi.chat.domain.model.readmessage.ReadMessageResult;
import com.tronxi.chat.infrastructure.api.rest.model.ReadMessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReadMessageMapper {

    public ReadMessageOrder toDomain(String conversationId, String userId) {
        return ReadMessageOrder.builder()
                .conversationId(conversationId)
                .userId(userId)
                .build();
    }

    public ReadMessageResponse toResponse(ReadMessageResult readMessageResult) {
        return ReadMessageResponse.builder()
                .message(readMessageResult.getMessage())
                .date(readMessageResult.getDate())
                .isMyMessage(readMessageResult.getIsMyMessage())
                .build();
    }

    public List<ReadMessageResponse> toResponse(List<ReadMessageResult> readMessageResultList) {
        return readMessageResultList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
