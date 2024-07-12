package net.likelion.dailytales.core.global.exception;

public class InvalidDateArgumentException extends APIException {
    public InvalidDateArgumentException() {
        super(ErrorCode.START_DATE_AFTER_END_DATE);
    }
}
