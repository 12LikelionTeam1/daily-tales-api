package net.likelion.dailytales.writing.api.dto.request;

import java.util.List;

public record RegisterWritingRequest(
        String title,
        String content,
        List<String> keywords,
        String commentary
) {
}
