package net.likelion.dailytales.writing.application;

import java.util.List;

public interface WritingCollectionRepository {

    void save(String userId, String writingId);

    boolean exists(String userId, String writingId);

    List<SimpleWritingDto> findWritingsByUserId(String userId);

    void delete(String userId, String writingId);

}
