package com.tronxi.chat.infrastructure.api.rest.controller;

import com.tronxi.chat.configuration.security.service.JwtService;
import com.tronxi.chat.domain.port.primary.CreateUser;
import com.tronxi.chat.domain.port.primary.FindAllUsers;
import com.tronxi.chat.domain.port.primary.FindConversations;
import com.tronxi.chat.infrastructure.api.rest.mapper.CreateUserMapper;
import com.tronxi.chat.infrastructure.api.rest.mapper.FindAllUsersMapper;
import com.tronxi.chat.infrastructure.api.rest.mapper.FindConversationMapper;
import com.tronxi.chat.infrastructure.api.rest.model.CreateUserRequest;
import com.tronxi.chat.infrastructure.api.rest.model.FindAllUsersResponse;
import com.tronxi.chat.infrastructure.api.rest.model.FindConversationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final CreateUser createUser;
    private final FindAllUsers findAllUsers;
    private final FindConversations findConversations;
    private final JwtService jwtService;

    private final CreateUserMapper createUserMapper;
    private final FindAllUsersMapper findAllUsersMapper;
    private final FindConversationMapper findConversationMapper;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest createUserRequest) {
        createUser.create(createUserMapper.toDomain(createUserRequest));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("authenticated")
    public ResponseEntity<List<FindAllUsersResponse>> findAllUsers(@RequestHeader("Authorization") String authorization) {
        String userId = jwtService.retrieveId(authorization);
        List<FindAllUsersResponse> findAllUsersResponseList = findAllUsersMapper.toResponse(findAllUsers.find(userId));
        return ResponseEntity.ok(findAllUsersResponseList);
    }

    @GetMapping("/{id}/conversations")
    @PreAuthorize("authenticated")
    public ResponseEntity<List<FindConversationResponse>> findAllConversationsByUserId(@PathVariable String id) {
        List<FindConversationResponse> findConversationResponseList = findConversationMapper.toResponse(findConversations.findByUserId(id));
        return ResponseEntity.ok(findConversationResponseList);
    }
}
