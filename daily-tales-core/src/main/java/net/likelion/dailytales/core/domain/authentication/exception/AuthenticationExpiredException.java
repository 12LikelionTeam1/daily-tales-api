package net.likelion.dailytales.core.domain.authentication.exception;

import net.likelion.dailytales.core.global.exception.APIException;
import net.likelion.dailytales.core.global.exception.ErrorCode;

public class AuthenticationExpiredException extends APIException {
    public AuthenticationExpiredException() {
        super(ErrorCode.AUTHENTICATION_EXPIRED);
    }
}
