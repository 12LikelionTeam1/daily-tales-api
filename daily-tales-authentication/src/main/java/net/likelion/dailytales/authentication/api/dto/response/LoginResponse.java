package net.likelion.dailytales.authentication.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.authentication.application.OAuthResult;

@JsonNaming(SnakeCaseStrategy.class)
public record LoginResponse(
        String accessToken,
        String refreshToken
) {
    public static LoginResponse from(OAuthResult oAuthResult) {
        return new LoginResponse(oAuthResult.accessToken().value(), oAuthResult.refreshToken().value());
    }
}
