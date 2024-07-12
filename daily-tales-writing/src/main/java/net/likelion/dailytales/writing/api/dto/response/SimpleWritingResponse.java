package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.likelion.dailytales.core.domain.writing.Visibility;
import net.likelion.dailytales.writing.application.SimpleWritingInfo;

import java.time.LocalDateTime;

public record SimpleWritingResponse(
        String id,
        String title,
        Visibility visibility,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime writtenAt
) {
    public static SimpleWritingResponse from(SimpleWritingInfo writing) {
        return new SimpleWritingResponse(
                writing.id(),
                writing.title(),
                writing.visibility(),
                writing.writtenAt()
        );
    }
}
