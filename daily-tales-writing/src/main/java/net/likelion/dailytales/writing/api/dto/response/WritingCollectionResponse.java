package net.likelion.dailytales.writing.api.dto.response;

import net.likelion.dailytales.writing.application.SimpleWritingDto;

import java.util.List;

public record WritingCollectionResponse(
        List<SimpleWritingResponse> collection
) {
    public static WritingCollectionResponse of(List<SimpleWritingDto> collection) {
        return new WritingCollectionResponse(
                collection.stream()
                        .map(SimpleWritingResponse::from)
                        .toList()
        );
    }
}
