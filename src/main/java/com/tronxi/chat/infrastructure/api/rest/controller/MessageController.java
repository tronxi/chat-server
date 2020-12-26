package com.tronxi.chat.infrastructure.api.rest.controller;


import com.tronxi.chat.domain.model.readmessage.ReadMessageResult;
import com.tronxi.chat.domain.port.primary.ReadMessage;
import com.tronxi.chat.domain.port.primary.SendMessage;
import com.tronxi.chat.infrastructure.api.rest.mapper.ReadMessageMapper;
import com.tronxi.chat.infrastructure.api.rest.mapper.SendMessageMapper;
import com.tronxi.chat.infrastructure.api.rest.model.ReadMessageResponse;
import com.tronxi.chat.infrastructure.api.rest.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("messages")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("authenticated")
public class MessageController {

    private final SendMessage sendMessage;
    private final ReadMessage readMessage;

    private final SendMessageMapper sendMessageMapper;
    private final ReadMessageMapper readMessageMapper;

    @PostMapping("/conversation/{conversationId}/user/{userId}")
    ResponseEntity<Void> sendMessage(@PathVariable String conversationId,
                                     @PathVariable String userId,
                                     @RequestBody SendMessageRequest sendMessageRequest) {

        sendMessage.send(sendMessageMapper.toDomain(sendMessageRequest, conversationId, userId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversation/{conversationId}/user/{userId}")
    ResponseEntity<List<ReadMessageResponse>> readMessages(@PathVariable String conversationId, @PathVariable String userId) {
        List<ReadMessageResult> readMessageResultList = readMessage.read(readMessageMapper.toDomain(conversationId, userId));
        return ResponseEntity.ok(readMessageMapper.toResponse(readMessageResultList));
    }
}
