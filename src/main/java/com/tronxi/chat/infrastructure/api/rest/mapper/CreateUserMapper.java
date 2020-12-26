package com.tronxi.chat.infrastructure.api.rest.mapper;

import com.tronxi.chat.domain.model.createuser.CreateUserOrder;
import com.tronxi.chat.infrastructure.api.rest.model.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateUserMapper {
    public CreateUserOrder toDomain(CreateUserRequest createUserRequest) {
        return CreateUserOrder.builder()
                .name(createUserRequest.getName())
                .password(createUserRequest.getPassword())
                .build();
    }
}
