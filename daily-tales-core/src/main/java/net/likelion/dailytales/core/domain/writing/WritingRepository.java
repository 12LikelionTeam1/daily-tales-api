package net.likelion.dailytales.core.domain.writing;

public interface WritingRepository {

    void save(Writing writing);

    String getWriterIdById(String id);

    Visibility getVisibilityById(String id);

    Writing findById(String id);

    boolean exists(String id);

    default boolean notExists(String id) {
        return !exists(id);
    }

    void deleteById(String id);

    void updateVisibility(String id, Visibility visibility);

    void updateCommentary(String id, String content);

}
