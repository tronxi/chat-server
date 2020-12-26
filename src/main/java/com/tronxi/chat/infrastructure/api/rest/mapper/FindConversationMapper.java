package com.tronxi.chat.infrastructure.api.rest.mapper;

import com.tronxi.chat.domain.model.findconversation.FindConversationResult;
import com.tronxi.chat.infrastructure.api.rest.model.FindConversationResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindConversationMapper {
    public FindConversationResponse toResponse(FindConversationResult findConversationResult) {
        return FindConversationResponse.builder()
                .conversationId(findConversationResult.getConversationId())
                .name(findConversationResult.getName())
                .unreadMessages(findConversationResult.getUnreadMessages())
                .build();
    }

    public List<FindConversationResponse> toResponse(List<FindConversationResult> findConversationResultList) {
        return findConversationResultList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
