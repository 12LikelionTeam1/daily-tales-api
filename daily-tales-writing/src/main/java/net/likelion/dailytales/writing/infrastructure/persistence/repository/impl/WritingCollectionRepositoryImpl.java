package net.likelion.dailytales.writing.infrastructure.persistence.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.writing.application.SimpleWritingDto;
import net.likelion.dailytales.writing.application.WritingCollectionRepository;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingCollectionEntity;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingCollectionEntityKey;
import net.likelion.dailytales.writing.infrastructure.persistence.projection.QWritingProjection;
import net.likelion.dailytales.writing.infrastructure.persistence.projection.WritingProjection;
import net.likelion.dailytales.writing.infrastructure.persistence.repository.jpa.JpaWritingCollectionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static net.likelion.dailytales.common.user.entity.QUserEntity.userEntity;
import static net.likelion.dailytales.writing.infrastructure.persistence.entity.QWritingCollectionEntity.writingCollectionEntity;
import static net.likelion.dailytales.writing.infrastructure.persistence.entity.QWritingEntity.writingEntity;

@Repository
@RequiredArgsConstructor
public class WritingCollectionRepositoryImpl implements WritingCollectionRepository {
    private final JpaWritingCollectionRepository jpaWritingCollectionRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public void save(String userId, String writingId) {
        jpaWritingCollectionRepository.save(new WritingCollectionEntity(userId, writingId));
    }

    @Override
    public boolean exists(String userId, String writingId) {
        return jpaWritingCollectionRepository.existsById(new WritingCollectionEntityKey(userId, writingId));
    }

    @Override
    public List<SimpleWritingDto> findWritingsByUserId(String userId) {
        return queryFactory
                .select(
                        new QWritingProjection(
                                writingEntity.id,
                                writingEntity.writerId,
                                userEntity.nickname,
                                userEntity.profileImageUrl,
                                writingEntity.title,
                                writingEntity.createdAt
                        )
                )
                .from(writingCollectionEntity)
                .leftJoin(writingEntity)
                .on(writingCollectionEntity.writingId.eq(writingEntity.id))
                .leftJoin(userEntity)
                .on(writingEntity.writerId.eq(userEntity.id))
                .where(
                        writingCollectionEntity.userId.eq(userId),
                        writingEntity.isNotNull(),
                        userEntity.isNotNull()
                )
                .fetch()
                .stream()
                .map(WritingProjection::toSimpleWritingIfo)
                .toList();
    }

    @Override
    public void delete(String userId, String writingId) {
        jpaWritingCollectionRepository.deleteById(new WritingCollectionEntityKey(userId, writingId));
    }
}
