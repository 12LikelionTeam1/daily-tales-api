package net.likelion.dailytales.writing.infrastructure.persistence.support;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.writing.Visibility;
import net.likelion.dailytales.writing.application.PagedSimpleWritingDto;
import net.likelion.dailytales.writing.application.SimpleWritingDto;
import net.likelion.dailytales.writing.application.WritingSearchSupport;
import net.likelion.dailytales.writing.infrastructure.persistence.PageUtil;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingEntity;
import net.likelion.dailytales.writing.infrastructure.persistence.projection.QWritingProjection;
import net.likelion.dailytales.writing.infrastructure.persistence.projection.WritingProjection;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static net.likelion.dailytales.common.user.entity.QUserEntity.userEntity;
import static net.likelion.dailytales.writing.infrastructure.persistence.entity.QWritingEntity.writingEntity;

@Repository
@RequiredArgsConstructor
public class QWritingSearchSupport implements WritingSearchSupport {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SimpleWritingDto> getWritingsOfUser(String userId, LocalDate startDate, LocalDate endDate) {
        return queryFactory
                .selectFrom(writingEntity)
                .where(
                        writingEntity.writerId.eq(userId),
                        betweenDate(startDate, endDate)
                )
                .fetch()
                .stream()
                .map(WritingEntity::toDto)
                .toList();
    }

    @Override
    public PagedSimpleWritingDto getWritingsExcludingUser(
            String userId,
            int page,
            int size
    ) {
        Long totalElements = queryFactory
                .select(writingEntity.count())
                .from(writingEntity)
                .where(
                        writingEntity.writerId.ne(userId),
                        writingEntity.visibility.eq(Visibility.PUBLIC)
                )
                .fetchFirst();
        List<SimpleWritingDto> contents = queryFactory
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
                .from(writingEntity)
                .leftJoin(userEntity)
                .on(writingEntity.writerId.eq(userEntity.id))
                .where(
                        writingEntity.writerId.ne(userId),
                        writingEntity.visibility.eq(Visibility.PUBLIC),
                        userEntity.isNotNull()
                )
                .offset((long) page * size)
                .limit(size)
                .fetch()
                .stream()
                .map(WritingProjection::toDto)
                .toList();
        return new PagedSimpleWritingDto(
                page,
                size,
                totalElements,
                PageUtil.getTotalPages(totalElements, size),
                contents
        );
    }

    private BooleanExpression betweenDate(LocalDate startDate, LocalDate endDate) {
        return writingEntity.createdAt.between(
                startDate.atStartOfDay(),
                endDate.atTime(LocalTime.MAX).minusNanos(999_999_999)
        );
    }
}
