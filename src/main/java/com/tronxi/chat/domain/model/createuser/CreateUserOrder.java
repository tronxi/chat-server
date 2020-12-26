package com.tronxi.chat.domain.model.createuser;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserOrder {
    String name;
    String password;
}
