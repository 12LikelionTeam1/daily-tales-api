package net.likelion.dailytales.writing.api.dto.response;

public record PublishedWritingsResponse(
        Long publishedWritings
) {
    public static PublishedWritingsResponse of(Long publishedWritings) {
        return new PublishedWritingsResponse(publishedWritings);
    }
}
