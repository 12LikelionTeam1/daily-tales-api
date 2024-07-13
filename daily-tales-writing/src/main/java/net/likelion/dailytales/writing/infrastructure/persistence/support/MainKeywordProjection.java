package net.likelion.dailytales.writing.infrastructure.persistence.support;

import com.querydsl.core.annotations.QueryProjection;

public record MainKeywordProjection(
        String keyword,
        Long frequency
) {
    @QueryProjection
    public MainKeywordProjection {
    }
}
