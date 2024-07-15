package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record PublishedWritingsResponse(
        Long publishedWritings
) {
    public static PublishedWritingsResponse of(Long publishedWritings) {
        return new PublishedWritingsResponse(publishedWritings);
    }
}
