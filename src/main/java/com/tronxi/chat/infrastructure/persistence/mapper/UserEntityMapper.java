package com.tronxi.chat.infrastructure.persistence.mapper;

import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {

    public User toDomain(UserEntity userEntity) {
        return User.builder()
                .name(userEntity.getName())
                .id(userEntity.getId())
                .password(userEntity.getPassword())
                .build();
    }

    public List<User> toDomain(List<UserEntity> userEntityList) {
        return userEntityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }


    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .build();
    }

    public List<UserEntity> toEntity(List<User> userList) {
        return userList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
