package net.likelion.dailytales.writing.application;

import net.likelion.dailytales.core.domain.writing.WritingCommentary;

public interface WringCommentaryRepository {

    void save(WritingCommentary wringCommentary);

    WritingCommentary findById(String id);

}
