package net.likelion.dailytales.authentication.application;

public record OAuthResource(
        OAuthType type,
        String socialId
) {
    public static OAuthResource of(OAuthType type, String socialId) {
        return new OAuthResource(type, socialId);
    }
}
