package net.likelion.dailytales.authentication.application;

public record OAuthRequestDto(
        OAuthType type,
        String code
) {
    public static OAuthRequestDto of(OAuthType type, String code) {
        return new OAuthRequestDto(type, code);
    }
}