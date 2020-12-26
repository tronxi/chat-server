package com.tronxi.chat.infrastructure.persistence.adapter;

import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.domain.port.secondary.UserRepository;
import com.tronxi.chat.infrastructure.persistence.entity.UserEntity;
import com.tronxi.chat.infrastructure.persistence.mapper.UserEntityMapper;
import com.tronxi.chat.infrastructure.persistence.repository.UserRepositoryJPA;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryAdapter implements UserRepository {

    private final UserRepositoryJPA userRepositoryJPA;
    private final UserEntityMapper userEntityMapper;
    @Override
    public Optional<User> findByName(String name) {
        return userRepositoryJPA.findByName(name)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findById(String userId) {
        return userRepositoryJPA.findById(userId)
                .map(userEntityMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userRepositoryJPA.findAll()
                .stream()
                .map(userEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        userRepositoryJPA.save(userEntityMapper.toEntity(user));
    }
}
