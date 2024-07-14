package net.likelion.dailytales.core.domain.user;

public record User(
        String id,
        String nickname,
        String displayId,
        String profileImageUrl
) {
}