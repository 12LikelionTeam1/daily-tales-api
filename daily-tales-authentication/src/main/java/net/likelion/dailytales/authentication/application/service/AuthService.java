package net.likelion.dailytales.authentication.application.service;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.TokenProvider;
import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenType;
import net.likelion.dailytales.core.domain.authentication.exception.AuthenticationFailedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;

    public Token refresh(String refreshToken) {
        Token token = tokenProvider.makeTokenFrom(refreshToken);
        if (token.type() != TokenType.REFRESH) {
            throw new AuthenticationFailedException();
        }
        String userId = tokenProvider.extractUserId(token);
        return tokenProvider.generateToken(TokenType.ACCESS, userId);
    }
}
