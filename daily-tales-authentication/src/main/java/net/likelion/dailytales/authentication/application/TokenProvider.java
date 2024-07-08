package net.likelion.dailytales.authentication.application;

import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenType;

public interface TokenProvider {

    Token generateToken(TokenType type, String userId);

    String extractUserId(Token token);

    Token makeTokenFrom(String value);

}
