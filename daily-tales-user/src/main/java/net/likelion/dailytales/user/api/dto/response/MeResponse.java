package net.likelion.dailytales.user.api.dto.response;

import net.likelion.dailytales.core.domain.user.User;

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
