package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.writing.application.statistics.TotalWritingsPerDayDto;

import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
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
