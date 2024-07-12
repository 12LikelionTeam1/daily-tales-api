package net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa;

import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaWritingKeywordRepository extends JpaRepository<WritingKeywordEntity, String> {

    List<WritingKeywordEntity> findAllByWritingId(String writingId);

    void deleteAllByWritingId(String writingId);

}
