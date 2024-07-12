package net.likelion.dailytales.core.domain.writing;

import net.likelion.dailytales.core.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record Writing(
        String id,
        User writer,
        String title,
        Visibility visibility,
        List<String> keywords,
        String content,
        WritingCommentary commentary,
        LocalDateTime createdAt
) {
    public static Writing newWriting(
            String id,
            User writer,
            String title,
            Visibility visibility,
            List<String> keywords,
            String content,
            WritingCommentary commentary
    ) {
        return new Writing(
                id,
                writer,
                title,
                visibility,
                keywords,
                content,
                commentary,
                LocalDateTime.now()
        );
    }
}
