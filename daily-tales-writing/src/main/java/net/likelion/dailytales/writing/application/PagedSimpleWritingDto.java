package net.likelion.dailytales.writing.application;

import java.util.List;

public record PagedSimpleWritingDto(
        Integer page,
        Integer size,
        Long totalElements,
        Integer totalPages,
        List<SimpleWritingDto> contents
) {
}
