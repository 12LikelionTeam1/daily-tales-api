package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.core.domain.writing.Visibility;
import net.likelion.dailytales.core.domain.writing.Writing;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
public record WritingResponse(
        String title,
        Visibility visibility,
        List<String> keywords,
        String content,
        String commentary,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime writtenAt
) {
    public static WritingResponse from(Writing writing) {
        return new WritingResponse(
                writing.title(),
                writing.visibility(),
                writing.keywords(),
                writing.content(),
                writing.commentary().content(),
                writing.createdAt()
        );
    }
}
