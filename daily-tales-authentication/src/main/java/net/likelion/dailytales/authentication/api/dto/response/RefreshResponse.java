package net.likelion.dailytales.authentication.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenType;

@JsonNaming(SnakeCaseStrategy.class)
public record RefreshResponse(
        String accessToken
) {
    public static RefreshResponse of(Token token) {
        if (token.type() != TokenType.ACCESS) {
            throw new IllegalArgumentException("Token type must be ACCESS");
        }
        return new RefreshResponse(token.value());
    }
}
