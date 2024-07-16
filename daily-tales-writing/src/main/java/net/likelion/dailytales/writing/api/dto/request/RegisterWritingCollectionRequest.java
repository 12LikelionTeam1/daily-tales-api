package net.likelion.dailytales.writing.api.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(SnakeCaseStrategy.class)
public record RegisterWritingCollectionRequest(
        String writingId
) {
}
