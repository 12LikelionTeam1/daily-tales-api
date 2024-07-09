package net.likelion.dailytales.authentication.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.api.dto.request.LoginRequest;
import net.likelion.dailytales.authentication.api.dto.response.LoginResponse;
import net.likelion.dailytales.authentication.application.OAuthRequestDto;
import net.likelion.dailytales.authentication.application.OAuthResult;
import net.likelion.dailytales.authentication.application.OAuthType;
import net.likelion.dailytales.authentication.application.service.OAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oauth/kakao")
@RequiredArgsConstructor
public class KakaoOAuthController {
    private final OAuthService oAuthService;

    @GetMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        OAuthResult result = oAuthService.authenticate(requestOf(request.accessToken()));

        return LoginResponse.from(result);
    }

    private static OAuthRequestDto requestOf(String accessToken) {
        return OAuthRequestDto.of(OAuthType.KAKAO, accessToken);
    }
}