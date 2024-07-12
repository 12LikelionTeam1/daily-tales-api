package net.likelion.dailytales.writing.application;

import net.likelion.dailytales.core.domain.writing.Visibility;
import java.time.LocalDateTime;

public record SimpleWritingInfo(
        String id,
        String title,
        Visibility visibility,
        LocalDateTime writtenAt
) {
}
