package net.likelion.dailytales.writing.infrastructure.persistence.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.writing.application.statistics.KeywordStatisticsSupport;
import net.likelion.dailytales.writing.application.statistics.MainKeywordDto;
import net.likelion.dailytales.writing.infrastructure.persistence.projection.QMainKeywordProjection;
import org.springframework.stereotype.Component;

import java.util.List;

import static net.likelion.dailytales.writing.infrastructure.persistence.entity.QWritingEntity.writingEntity;
import static net.likelion.dailytales.writing.infrastructure.persistence.entity.QWritingKeywordEntity.writingKeywordEntity;

@Component
@RequiredArgsConstructor
public class QKeywordStatisticsSupport implements KeywordStatisticsSupport {
    private final JPAQueryFactory queryFactory;

    public List<MainKeywordDto> getMainKeywords(String userId, Integer size) {
        return queryFactory
                .select(
                        new QMainKeywordProjection(
                                writingKeywordEntity.keyword,
                                writingKeywordEntity.keyword.count()
                        )
                )
                .from(writingKeywordEntity)
                .leftJoin(writingEntity)
                .on(writingKeywordEntity.writingId.eq(writingEntity.id))
                .where(writingEntity.writerId.eq(userId))
                .groupBy(writingKeywordEntity.keyword)
                .orderBy(writingKeywordEntity.keyword.count().desc())
                .limit(size)
                .fetch()
                .stream().map(
                        row -> new MainKeywordDto(
                                row.keyword(),
                                row.frequency().intValue()
                        )
                )
                .toList();
    }
}
