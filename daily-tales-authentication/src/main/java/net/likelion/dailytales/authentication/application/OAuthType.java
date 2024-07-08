package net.likelion.dailytales.authentication.application;

public enum OAuthType {
    KAKAO("kakao"),
    GOOGLE("google");

    private final String value;

    OAuthType(String value) {
        this.value = value;
    }

    public static OAuthType of(String type) {
        return OAuthType.valueOf(type.toUpperCase());
    }

    public String value() {
        return value;
    }
}
