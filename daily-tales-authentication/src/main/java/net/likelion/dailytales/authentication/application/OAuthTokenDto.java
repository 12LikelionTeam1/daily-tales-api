package net.likelion.dailytales.authentication.application;

public record OAuthTokenDto(
        OAuthType type,
        String code
) {
    public static OAuthTokenDto of(OAuthType type, String code) {
        return new OAuthTokenDto(type, code);
    }
}