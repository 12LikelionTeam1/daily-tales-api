package net.likelion.dailytales.writing.infrastructure.persistence.support;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.writing.application.statistics.TotalWritingsCountSupport;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.likelion.dailytales.writing.infrastructure.persistence.entity.QWritingEntity.writingEntity;

@Component
@RequiredArgsConstructor
public class QTotalWritingsCountSupport implements TotalWritingsCountSupport {
    private final JPAQueryFactory queryFactory;

    @Override
    public Map<Month, Integer> getTotalWritingsPerMonth(String userId, Year year) {
        return queryFactory
                .select(
                        writingEntity.createdAt.month(),
                        writingEntity.count()
                )
                .from(writingEntity)
                .where(
                        writingEntity.writerId.eq(userId),
                        writingEntity.createdAt.year().eq(year.getValue())
                )
                .groupBy(writingEntity.createdAt.month())
                .fetch()
                .stream()
                .collect(Collectors.toMap(this::monthOf, this::countOf));
    }

    @Override
    public Map<Integer, Integer> getTotalWritingsPerDay(String userId, Year year, Month month) {
        return queryFactory
                .select(
                        writingEntity.createdAt.dayOfMonth(),
                        writingEntity.count()
                )
                .from(writingEntity)
                .where(
                        writingEntity.writerId.eq(userId),
                        writingEntity.createdAt.year().eq(year.getValue()),
                        writingEntity.createdAt.month().eq(month.getValue())
                )
                .groupBy(writingEntity.createdAt.dayOfMonth())
                .fetch()
                .stream()
                .collect(
                        Collectors.toMap(
                                row -> row.get(0, Integer.class),
                                this::countOf
                        )
                );
    }

    private Month monthOf(Tuple row) {
        return Month.of(row.get(0, Integer.class));
    }

    private Integer countOf(Tuple row) {
        return row.get(1, Long.class).intValue();
    }
}
