package com.tronxi.chat.domain.port.primary;

import com.tronxi.chat.domain.model.User;

import java.util.List;

public interface FindAllUsers {
    List<User> find(String userId);
}
