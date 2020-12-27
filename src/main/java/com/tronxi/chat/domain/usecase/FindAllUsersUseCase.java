package com.tronxi.chat.domain.usecase;

import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.port.primary.FindAllUsers;
import com.tronxi.chat.domain.port.secondary.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllUsersUseCase implements FindAllUsers {

    private final UserRepository userRepository;
    @Override
    public List<User> find(String userId) {
        log.info("Finding all users");
        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getId().equals(userId))
                .collect(Collectors.toList());
    }
}
