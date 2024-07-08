package net.likelion.dailytales.core.global.exception;

public enum ErrorCode {
    // Common
    AUTHENTICATION_FAILED("C0001", "인증에 실패했습니다."),
    AUTHENTICATION_REQUIRED("C0002", "인증을 필요로 합니다."),
    AUTHENTICATION_EXPIRED("C0003", "인증이 만료되었습니다."),

    NOT_FOUND("C0003", "해당 자원을 찾을 수 없습니다.");

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