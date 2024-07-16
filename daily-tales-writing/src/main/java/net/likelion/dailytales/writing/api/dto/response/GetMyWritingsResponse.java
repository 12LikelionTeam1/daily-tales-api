package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.writing.api.dto.SimpleWritingResponse;
import net.likelion.dailytales.writing.application.SimpleWritingDto;

import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record GetMyWritingsResponse(
        List<SimpleWritingResponse> writings
) {
    public static GetMyWritingsResponse from(List<SimpleWritingDto> writings) {
        return new GetMyWritingsResponse(
                writings.stream()
                        .map(SimpleWritingResponse::from)
                        .toList()
        );
    }
}
