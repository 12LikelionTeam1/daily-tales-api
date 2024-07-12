package net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa;

import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWritingRepository extends JpaRepository<WritingEntity, String> {
}
