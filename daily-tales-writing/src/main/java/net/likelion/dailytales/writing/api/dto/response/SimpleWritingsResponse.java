package net.likelion.dailytales.writing.api.dto.response;

import net.likelion.dailytales.writing.application.SimpleWritingInfo;

import java.util.List;

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
