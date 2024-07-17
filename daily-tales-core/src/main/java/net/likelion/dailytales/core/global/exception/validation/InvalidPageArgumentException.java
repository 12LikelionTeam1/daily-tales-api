package net.likelion.dailytales.core.global.exception.validation;

import net.likelion.dailytales.core.global.exception.APIException;
import net.likelion.dailytales.core.global.exception.ErrorCode;

public class InvalidPageArgumentException extends APIException {
    public InvalidPageArgumentException() {
        super(ErrorCode.INVALID_PAGE_ARGUMENT);
    }
}
