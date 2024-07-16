package net.likelion.dailytales.writing.application;

import jakarta.annotation.Nullable;
import net.likelion.dailytales.core.domain.writing.Visibility;
import java.time.LocalDateTime;

public record SimpleWritingDto(
        String id,
        String title,
        @Nullable WriterDto writer,
        @Nullable Visibility visibility,
        LocalDateTime writtenAt
) {
}
