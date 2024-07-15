package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.writing.application.statistics.TotalWritingsPerMonthDto;

import java.time.Month;
import java.util.Map;

@JsonNaming(SnakeCaseStrategy.class)
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
