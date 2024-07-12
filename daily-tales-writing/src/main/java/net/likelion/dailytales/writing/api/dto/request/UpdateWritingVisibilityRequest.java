package net.likelion.dailytales.writing.api.dto.request;

import net.likelion.dailytales.core.domain.writing.Visibility;

public record UpdateWritingVisibilityRequest(
        Visibility visibility
) {
}
