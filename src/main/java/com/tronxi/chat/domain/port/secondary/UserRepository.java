package com.tronxi.chat.domain.port.secondary;

import com.tronxi.chat.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByName(String name);
    Optional<User> findById(String userId);
    List<User> findAll();
    void save(User user);
}
