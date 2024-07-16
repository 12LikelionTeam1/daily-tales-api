package net.likelion.dailytales.writing.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.core.domain.writing.Visibility;
import net.likelion.dailytales.writing.application.SimpleWritingDto;
import net.likelion.dailytales.writing.application.WriterDto;

import java.time.LocalDateTime;

@JsonNaming(SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SimpleWritingResponse(
        String id,
        String title,
        WriterResponse writer,
        Visibility visibility,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime writtenAt
) {
    public static SimpleWritingResponse from(SimpleWritingDto writing) {
        return new SimpleWritingResponse(
                writing.id(),
                writing.title(),
                writing.writer() == null ? null : WriterResponse.from(writing.writer()),
                writing.visibility(),
                writing.writtenAt()
        );
    }

    @JsonNaming(SnakeCaseStrategy.class)
    record WriterResponse(
            String id,
            String nickname,
            String profileImageUrl
    ) {
        public static WriterResponse from(WriterDto writer) {
            return new WriterResponse(
                    writer.id(),
                    writer.nickname(),
                    writer.profileImageUrl()
            );
        }
    }
}
