package net.likelion.dailytales.core.domain.writing;

public record WritingCommentary(
        String content
) {
    public static WritingCommentary of(String content) {
        return new WritingCommentary(content);
    }
}
