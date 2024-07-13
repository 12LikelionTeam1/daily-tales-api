package net.likelion.dailytales.writing.application.statistics;

import java.time.Month;
import java.util.Map;

public record TotalWritingsPerMonthDto(
        Integer totalWritingsOfYear,
        Map<Month, Integer> totalWritingsPerMonth
) {
}
