package net.likelion.dailytales.authentication.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.api.dto.response.LoginResponse;
import net.likelion.dailytales.authentication.application.OAuthRequestDto;
import net.likelion.dailytales.authentication.application.OAuthResult;
import net.likelion.dailytales.authentication.application.OAuthType;
import net.likelion.dailytales.authentication.application.service.OAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth/kakao")
@RequiredArgsConstructor
public class KakaoOAuthController {
    private final OAuthService oAuthService;

    @GetMapping
    public LoginResponse login(@RequestParam("code") String code) {
        OAuthResult result = oAuthService.authenticate(request(code));

        return LoginResponse.from(result);
    }

    private static OAuthRequestDto request(String code) {
        return OAuthRequestDto.of(OAuthType.KAKAO, code);
    }
}