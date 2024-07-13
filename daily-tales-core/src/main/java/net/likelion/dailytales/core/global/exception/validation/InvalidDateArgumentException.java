package net.likelion.dailytales.core.global.exception.validation;

import net.likelion.dailytales.core.global.exception.APIException;
import net.likelion.dailytales.core.global.exception.ErrorCode;

public class InvalidDateArgumentException extends APIException {
    public InvalidDateArgumentException() {
        super(ErrorCode.START_DATE_AFTER_END_DATE);
    }
}
