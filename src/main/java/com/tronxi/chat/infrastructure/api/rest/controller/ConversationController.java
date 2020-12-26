package com.tronxi.chat.infrastructure.api.rest.controller;

import com.tronxi.chat.domain.model.Conversation;
import com.tronxi.chat.domain.port.primary.CreateConversation;
import com.tronxi.chat.infrastructure.api.rest.mapper.CreateConversationMapper;
import com.tronxi.chat.infrastructure.api.rest.model.CreateConversationRequest;
import com.tronxi.chat.infrastructure.api.rest.model.CreateConversationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("conversations")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("authenticated")
public class ConversationController {

    private final CreateConversation createConversation;

    private final CreateConversationMapper createConversationMapper;

    @PostMapping("/{id}")
    public ResponseEntity<CreateConversationResponse> createConversation(@RequestBody CreateConversationRequest createConversationRequest,
                                                                         @PathVariable String id) {
        Conversation conversation = createConversation.create(createConversationMapper.toDomain(createConversationRequest, id));
        return ResponseEntity.ok(createConversationMapper.toResponse(conversation));
    }
}
