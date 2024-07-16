package net.likelion.dailytales.writing.infrastructure.persistence.support;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.writing.application.SimpleWritingDto;
import net.likelion.dailytales.writing.application.WritingSearchSupport;
import net.likelion.dailytales.writing.infrastructure.persistence.entity.WritingEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    private BooleanExpression betweenDate(LocalDate startDate, LocalDate endDate) {
        return writingEntity.createdAt.between(
                startDate.atStartOfDay(),
                endDate.atTime(LocalTime.MAX).minusNanos(999_999_999)
        );
    }
}
