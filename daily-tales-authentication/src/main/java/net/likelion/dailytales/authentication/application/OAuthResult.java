package net.likelion.dailytales.authentication.application;

import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenType;

public record OAuthResult(
        Token accessToken,
        Token refreshToken
) {
    public OAuthResult(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        if (accessToken.type() != TokenType.ACCESS || refreshToken.type() != TokenType.REFRESH) {
            throw new IllegalArgumentException("Token type is invalid");
        }
    }
}
