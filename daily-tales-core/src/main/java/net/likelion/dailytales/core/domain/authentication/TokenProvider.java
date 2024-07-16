package net.likelion.dailytales.core.domain.authentication;

public interface TokenProvider {

    Token generateToken(TokenType type, String userId);

    String extractUserId(Token token);

    Token makeTokenFrom(String value);

}
