package net.likelion.dailytales.writing.infrastructure.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.common.user.entity.UserEntity;
import net.likelion.dailytales.common.user.repository.jpa.JpaUserRepository;
import net.likelion.dailytales.core.domain.user.exception.UserNotFoundException;
import net.likelion.dailytales.core.domain.writing.Visibility;
import net.likelion.dailytales.core.domain.writing.Writing;
import net.likelion.dailytales.core.domain.writing.WritingRepository;
import net.likelion.dailytales.core.domain.writing.exception.WritingNotFoundException;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingCommentaryEntity;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingEntity;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingKeywordEntity;
import net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa.JpaWritingCommentaryRepository;
import net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa.JpaWritingKeywordRepository;
import net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa.JpaWritingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WritingRepositoryImpl implements WritingRepository {
    private final JpaWritingRepository jpaWritingRepository;
    private final JpaWritingKeywordRepository jpaWritingKeywordRepository;
    private final JpaWritingCommentaryRepository jpaWritingCommentaryRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public void save(Writing writing) {
        jpaWritingRepository.save(WritingEntity.from(writing));
        jpaWritingKeywordRepository.saveAll(WritingKeywordEntity.from(writing.id(), writing.keywords()));
        jpaWritingCommentaryRepository.save(WritingCommentaryEntity.from(writing));
    }

    @Override
    public String findUserIdById(String id) {
        return jpaWritingRepository
                .findById(id)
                .map(WritingEntity::getWriterId)
                .orElse(null);
    }

    @Override
    public Writing findById(String id) {
        WritingEntity writing = jpaWritingRepository
                .findById(id)
                .orElse(null);
        WritingCommentaryEntity commentary = jpaWritingCommentaryRepository
                .findById(id)
                .orElse(null);
        List<WritingKeywordEntity> keywords = jpaWritingKeywordRepository.findAllByWritingId(id);
        return collectToDomain(writing, commentary, keywords);
    }

    @Override
    public boolean exists(String id) {
        return jpaWritingRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        jpaWritingRepository.deleteById(id);
        jpaWritingCommentaryRepository.deleteById(id);
        jpaWritingKeywordRepository.deleteAllByWritingId(id);
    }

    @Override
    public void updateVisibility(String id, Visibility visibility) {
        WritingEntity writing = jpaWritingRepository
                .findById(id)
                .orElseThrow(WritingNotFoundException::new);
        jpaWritingRepository.save(writing.applyUpdateVisibility(visibility));
    }

    @Override
    public void updateCommentary(String id, String content) {
        WritingCommentaryEntity commentary = jpaWritingCommentaryRepository
                .findById(id)
                .orElseThrow(WritingNotFoundException::new);
        jpaWritingCommentaryRepository.save(commentary.applyUpdateCommentary(content));
    }

    private Writing collectToDomain(
            WritingEntity writing,
            WritingCommentaryEntity commentary,
            List<WritingKeywordEntity> keywords
    ) {
        if (writing == null || commentary == null) {
            return null;
        }
        UserEntity user = jpaUserRepository
                .findById(writing.getWriterId())
                .orElseThrow(UserNotFoundException::new);
        return new Writing(
                writing.getId(),
                user.toDomain(),
                writing.getTitle(),
                writing.getVisibility(),
                keywords.stream().map(WritingKeywordEntity::getKeyword).toList(),
                writing.getContent(),
                commentary.toDomain(),
                writing.getCreatedAt()
        );
    }
}
