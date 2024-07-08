package net.likelion.dailytales.authentication.application;

public record SocialAccount(
        OAuthType type,
        String socialId,
        String userId
) {
    public static SocialAccount of(OAuthType type, String socialId, String userId) {
        return new SocialAccount(type, socialId, userId);
    }
}
