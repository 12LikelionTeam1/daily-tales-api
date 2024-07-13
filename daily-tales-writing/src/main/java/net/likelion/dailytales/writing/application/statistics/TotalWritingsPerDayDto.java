package net.likelion.dailytales.writing.application.statistics;

import java.util.List;

public record TotalWritingsPerDayDto(
        List<Integer> totalWritingsPerDay
) {
}
