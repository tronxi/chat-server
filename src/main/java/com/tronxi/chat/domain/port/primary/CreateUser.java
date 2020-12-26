package com.tronxi.chat.domain.port.primary;

import com.tronxi.chat.domain.model.createuser.CreateUserOrder;

public interface CreateUser {
    void create(CreateUserOrder createUserOrder);
}
