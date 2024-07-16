package net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa;

import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingCollectionEntity;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingCollectionEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWritingCollectionRepository extends JpaRepository<WritingCollectionEntity, WritingCollectionEntityKey> {
}
