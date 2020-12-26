package com.tronxi.chat.infrastructure.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindConversationResponse {
    private String conversationId;
    private String name;
    private Long unreadMessages;
}
