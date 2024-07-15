package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.writing.application.SimpleWritingInfo;

import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
public record SimpleWritingsResponse(
        List<SimpleWritingResponse> writings
) {
    public static SimpleWritingsResponse from(List<SimpleWritingInfo> writings) {
        return new SimpleWritingsResponse(
                writings.stream()
                        .map(SimpleWritingResponse::from)
                        .toList()
        );
    }
}
