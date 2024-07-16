package net.likelion.dailytales.writing.api.dto.request;

public record RegisterWritingRequest(
        String title,
        String content,
        String commentary
) {
}
