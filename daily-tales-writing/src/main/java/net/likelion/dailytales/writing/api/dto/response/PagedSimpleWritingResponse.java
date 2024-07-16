package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.common.PagedResponse;
import net.likelion.dailytales.writing.application.PagedSimpleWritingDto;

import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
public class PagedSimpleWritingResponse extends PagedResponse<SimpleWritingResponse> {
    private PagedSimpleWritingResponse(
            Integer page,
            Integer size,
            Long totalElements,
            Integer totalPages,
            List<SimpleWritingResponse> contents
    ) {
        super(page, size, totalElements, totalPages, contents);
    }

    public static PagedSimpleWritingResponse of(PagedSimpleWritingDto writings) {
        return new PagedSimpleWritingResponse(
                writings.page(),
                writings.size(),
                writings.totalElements(),
                writings.totalPages(),
                writings.contents().stream().map(SimpleWritingResponse::from).toList()
        );
    }
}
