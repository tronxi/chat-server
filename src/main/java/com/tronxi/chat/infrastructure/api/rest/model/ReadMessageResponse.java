package com.tronxi.chat.infrastructure.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadMessageResponse {
    private String message;
    private Date date;
    private Boolean isMyMessage;
}
