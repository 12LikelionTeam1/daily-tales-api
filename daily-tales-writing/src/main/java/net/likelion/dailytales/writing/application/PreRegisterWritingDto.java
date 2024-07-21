package net.likelion.dailytales.writing.application;

import net.likelion.dailytales.core.domain.writing.WritingCommentary;

import java.util.List;

public record PreRegisterWritingDto(
        String writerId,
        String title,
        List<String> keywords,
        String content,
        WritingCommentary commentary
) {
}
