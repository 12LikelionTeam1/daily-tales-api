package net.likelion.dailytales.writing.infrastructure.persistence.projection;

import com.querydsl.core.annotations.QueryProjection;
import net.likelion.dailytales.writing.application.SimpleWritingDto;
import net.likelion.dailytales.writing.application.WriterDto;

import java.time.LocalDateTime;

public record WritingProjection(
        String id,
        String writerId,
        String writerNickname,
        String writerProfileImageUrl,
        String title,
        LocalDateTime writtenAt
) {
    @QueryProjection
    public WritingProjection {
    }

    public SimpleWritingDto toDto() {
        return new SimpleWritingDto(
                id,
                title,
                new WriterDto(
                        writerId,
                        writerNickname,
                        writerProfileImageUrl
                ),
                null,
                writtenAt
        );
    }
}
