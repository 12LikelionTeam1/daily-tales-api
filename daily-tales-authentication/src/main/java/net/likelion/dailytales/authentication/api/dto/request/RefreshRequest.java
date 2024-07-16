package net.likelion.dailytales.authentication.api.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record RefreshRequest(
    String refreshToken
) {
    public static RefreshRequest of(String refreshToken) {
        return new RefreshRequest(refreshToken);
    }
}
