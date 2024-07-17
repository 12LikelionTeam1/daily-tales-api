package net.likelion.dailytales.core.global;

import net.likelion.dailytales.core.global.exception.validation.InvalidPageArgumentException;

public class PageValidator {
    public static void validatePageRequest(int page, int size) {
        if (page < 0) {
            throw new InvalidPageArgumentException();
        }
        if (size < 1) {
            throw new InvalidPageArgumentException();
        }
    }
}
