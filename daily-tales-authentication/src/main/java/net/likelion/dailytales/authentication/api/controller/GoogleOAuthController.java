package net.likelion.dailytales.authentication.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.api.dto.request.LoginRequest;
import net.likelion.dailytales.authentication.api.dto.response.LoginResponse;
import net.likelion.dailytales.authentication.application.OAuthTokenDto;
import net.likelion.dailytales.authentication.application.OAuthResult;
import net.likelion.dailytales.authentication.application.OAuthType;
import net.likelion.dailytales.authentication.application.service.OAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oauth/google")
@RequiredArgsConstructor
public class GoogleOAuthController {
    private final OAuthService oAuthService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        OAuthResult result = oAuthService.authenticate(tokenOf(request.token()));

        return LoginResponse.from(result);
    }

    private static OAuthTokenDto tokenOf(String accessToken) {
        return OAuthTokenDto.of(OAuthType.GOOGLE, accessToken);
    }
}
