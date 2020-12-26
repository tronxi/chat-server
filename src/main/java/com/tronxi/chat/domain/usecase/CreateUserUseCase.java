package com.tronxi.chat.domain.usecase;

import com.tronxi.chat.domain.exception.UserAlreadyExistsException;
import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.model.createuser.CreateUserOrder;
import com.tronxi.chat.domain.port.primary.CreateUser;
import com.tronxi.chat.domain.port.secondary.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserUseCase implements CreateUser {

    private final UserRepository userRepository;

    @Override
    public void create(CreateUserOrder createUserOrder) {
        log.info("Creating user with name {}", createUserOrder.getName());

        Optional<User> userMaybe =  userRepository.findByName(createUserOrder.getName());
        if (userMaybe.isPresent()) {
            log.error("User with name {} already exists", createUserOrder.getName());
            throw new UserAlreadyExistsException("El usuaro ya existe");
        }
        userRepository.save(User.fromCreateUser(createUserOrder));

        log.info("Created user with name {}", createUserOrder.getName());
    }
}
