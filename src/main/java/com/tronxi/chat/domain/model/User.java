package com.tronxi.chat.domain.model;

import com.tronxi.chat.domain.model.createuser.CreateUserOrder;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class User {
    String id;
    String name;
    String password;

    public static User fromCreateUser(CreateUserOrder createUserOrder) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .name(createUserOrder.getName())
                .password(createUserOrder.getPassword())
                .build();
    }
}
