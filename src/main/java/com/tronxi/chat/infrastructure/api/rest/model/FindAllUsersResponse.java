package com.tronxi.chat.infrastructure.api.rest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindAllUsersResponse {
    private String id;
    private String name;
}
