package net.likelion.dailytales.writing.api.dto.request;

import java.util.List;

public record RegisterWritingRequest(
        String title,
        List<String> keywords,
        String content,
        String commentary
) {
}
