package net.likelion.dailytales.writing.application;

import java.util.List;

public interface WritingKeywordRepository {

    void save(String writingId, String keyword);

    void save(String writingId, List<String> keywords);

}
