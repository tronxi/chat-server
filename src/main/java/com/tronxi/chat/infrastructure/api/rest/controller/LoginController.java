package com.tronxi.chat.infrastructure.api.rest.controller;

import com.tronxi.chat.configuration.security.service.JwtService;
import com.tronxi.chat.domain.port.secondary.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final JwtService jwtService;
    private final UserRepository userRepository;


    @PreAuthorize("authenticated")
    @PostMapping
    public String login(@AuthenticationPrincipal User activeUser) {
        log.info("Login user with name {}", activeUser.getUsername());
        com.tronxi.chat.domain.model.User user = userRepository.findByName(activeUser.getUsername()).get();
        return jwtService.createToken(activeUser.getUsername(), user.getId());
    }
}
