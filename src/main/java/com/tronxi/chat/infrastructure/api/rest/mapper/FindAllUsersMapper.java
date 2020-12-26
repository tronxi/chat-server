package com.tronxi.chat.infrastructure.api.rest.mapper;

import com.tronxi.chat.domain.model.User;
import com.tronxi.chat.infrastructure.api.rest.model.FindAllUsersResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAllUsersMapper {

    public FindAllUsersResponse toResponse(User user) {
        return FindAllUsersResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public List<FindAllUsersResponse> toResponse(List<User> user) {
        return user.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
