package net.likelion.dailytales.user.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.core.domain.user.User;

@JsonNaming(SnakeCaseStrategy.class)
public record MeResponse(
        String displayId,
        String nickname,
        String profileImageUrl
) {
    public static MeResponse of(User user) {
        return new MeResponse(
                user.displayId(),
                user.nickname(),
                user.profileImageUrl()
        );
    }
}
