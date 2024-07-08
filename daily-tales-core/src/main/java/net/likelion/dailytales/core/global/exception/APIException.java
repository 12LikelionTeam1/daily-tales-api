package net.likelion.dailytales.core.global.exception;

public class APIException extends RuntimeException {
    private final ErrorCode errorCode;

    public APIException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public ErrorCode errorCode() {
        return errorCode;
    }
}
