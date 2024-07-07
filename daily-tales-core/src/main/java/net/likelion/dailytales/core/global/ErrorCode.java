package net.likelion.dailytales.core.global;

public enum ErrorCode {
    // Common
    AUTHENTICATION_FAILED("C0001", "Authentication failed");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}