package net.likelion.dailytales.core.domain.writing.exception;

import net.likelion.dailytales.core.global.exception.APIException;
import net.likelion.dailytales.core.global.exception.ErrorCode;

public class WritingNotFoundException extends APIException {
    public WritingNotFoundException() {
        super(ErrorCode.WRITING_NOT_FOUND);
    }
}
