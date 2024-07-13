package net.likelion.dailytales.writing.api.dto.response;

import net.likelion.dailytales.writing.application.statistics.TotalWritingsPerDayDto;

import java.util.List;

public record TotalWritingsPerDayResponse(
        List<Integer> totalWritingsPerDay
) {
    public static TotalWritingsPerDayResponse of(
            TotalWritingsPerDayDto totalWritings
    ) {
        return new TotalWritingsPerDayResponse(
                totalWritings.totalWritingsPerDay()
        );
    }
}
