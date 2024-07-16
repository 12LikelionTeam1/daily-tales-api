package net.likelion.dailytales.authentication.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.api.dto.request.RefreshRequest;
import net.likelion.dailytales.authentication.api.dto.response.RefreshResponse;
import net.likelion.dailytales.authentication.application.service.AuthService;
import net.likelion.dailytales.core.domain.authentication.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/refresh")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping
    public RefreshResponse refresh(@RequestBody RefreshRequest request) {
        Token accessToken = authService.refresh(request.refreshToken());

        return RefreshResponse.of(accessToken);
    }
}
