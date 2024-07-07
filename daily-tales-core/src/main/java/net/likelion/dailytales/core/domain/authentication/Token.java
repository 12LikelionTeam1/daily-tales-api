package net.likelion.dailytales.core.domain.authentication;

public class Token {
    private final String value;
    private final TokenType type;

    public Token(String value, TokenType type) {
        this.value = value;
        this.type = type;
    }

    public String value() {
        return value;
    }

    public TokenType type() {
        return type;
    }
}
