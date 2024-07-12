package net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa;

import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingCommentaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWritingCommentaryRepository extends JpaRepository<WritingCommentaryEntity, String> {
}
