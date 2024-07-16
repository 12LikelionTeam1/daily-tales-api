package net.likelion.dailytales.core.global.exception;

public enum ErrorCode {
    // Common
    AUTHENTICATION_FAILED("C0001", "인증에 실패했습니다."),
    AUTHENTICATION_REQUIRED("C0002", "인증을 필요로 합니다."),
    AUTHENTICATION_EXPIRED("C0003", "인증이 만료되었습니다."),
    NOT_FOUND("C0003", "해당 자원을 찾을 수 없습니다."),

    // Validation
    START_DATE_AFTER_END_DATE("V0001", "시작일은 종료일 이전이어야 합니다."),
    INVALID_DATE_FORMAT("V0002", "날짜 형식이 올바르지 않습니다."),

    // User
    USER_NOT_FOUND("U0001", "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS("U0002", "이미 존재하는 사용자입니다."),

    // Writing
    WRITING_NOT_FOUND("W0001", "글을 찾을 수 없습니다."),
    WRITING_NOT_REGISTERED("W0002", "예상치 못한 문제로 글이 등록되지 않았습니다.");

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