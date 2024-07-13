package net.likelion.dailytales.writing.api.dto.response;

import net.likelion.dailytales.writing.application.statistics.TotalWritingsPerMonthDto;

import java.time.Month;
import java.util.Map;

public record TotalWritingsPerMonthResponse(
        Integer totalWritingsOfYear,
        Map<Month, Integer> totalWritingsPerMonth
) {
    public static TotalWritingsPerMonthResponse of(TotalWritingsPerMonthDto totalWritings) {
        return new TotalWritingsPerMonthResponse(
                totalWritings.totalWritingsOfYear(),
                totalWritings.totalWritingsPerMonth()
        );
    }
}
