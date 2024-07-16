package net.likelion.dailytales.writing.infrastructure.persistence;

public class PageUtil {
    public static Integer getTotalPages(Long totalElements, Integer pageSize) {
        return (int) Math.ceil(totalElements * 1.0 / pageSize);
    }
}
