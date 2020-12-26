package com.tronxi.chat.infrastructure.api.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Error {
    String message;
}
