package net.likelion.dailytales.common;

import lombok.Getter;

import java.util.List;

@Getter
public class PagedResponse<T> {
    private final Integer page;
    private final Integer size;
    private final Long totalElements;
    private final Integer totalPages;
    private final List<T> contents;

    public PagedResponse(Integer page, Integer size, Long totalElements, Integer totalPages, List<T> contents) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.contents = contents;
    }
}
