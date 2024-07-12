package net.likelion.dailytales.core.domain.writing;

import java.util.List;

public interface WritingRepository {

    void save(Writing writing);

    Writing findById(String id);

    boolean exists(String id);

    default boolean notExists(String id) {
        return !exists(id);
    }

    void deleteById(String id);

    void updateVisibility(String id, Visibility visibility);

    void updateCommentary(String id, String content);

}
