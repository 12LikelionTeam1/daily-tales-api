package net.likelion.dailytales.core.global.exception;

public class ErrorResponse {
    private final String code;
    private final String message;

    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.code(), errorCode.message());
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
