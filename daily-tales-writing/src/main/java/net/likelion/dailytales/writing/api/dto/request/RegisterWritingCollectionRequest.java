package net.likelion.dailytales.writing.api.dto.request;

public record RegisterWritingCollectionRequest(
        String writingId,
        String writerId
) {
}
