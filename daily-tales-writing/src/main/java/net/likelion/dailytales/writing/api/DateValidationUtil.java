package net.likelion.dailytales.writing.api;

import net.likelion.dailytales.core.global.exception.validation.InvalidDateArgumentException;

import java.time.LocalDate;

public class DateValidationUtil {
    public static void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateArgumentException();
        }
    }
}
