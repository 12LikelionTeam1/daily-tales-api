package net.likelion.dailytales.core.domain.authentication.exception;

import net.likelion.dailytales.core.global.exception.APIException;
import net.likelion.dailytales.core.global.exception.ErrorCode;

public class AuthenticationFailedException extends APIException {
    public AuthenticationFailedException() {
        super(ErrorCode.AUTHENTICATION_FAILED);
    }
}
