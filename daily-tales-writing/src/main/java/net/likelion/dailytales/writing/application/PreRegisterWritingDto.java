package net.likelion.dailytales.writing.application;

import net.likelion.dailytales.core.domain.writing.WritingCommentary;

public record PreRegisterWritingDto(
        String writerId,
        String title,
        String content,
        WritingCommentary commentary
) {
}
