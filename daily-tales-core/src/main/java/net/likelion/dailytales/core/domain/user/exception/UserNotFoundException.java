package net.likelion.dailytales.core.domain.user.exception;

import net.likelion.dailytales.core.global.exception.APIException;
import net.likelion.dailytales.core.global.exception.ErrorCode;

public class UserNotFoundException extends APIException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
