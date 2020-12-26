package com.tronxi.chat.domain.service;

import com.tronxi.chat.domain.exception.UserNotFound;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.port.secondary.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class UserRetriever {

    private final UserRepository userRepository;

    public User findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));
    }
}
