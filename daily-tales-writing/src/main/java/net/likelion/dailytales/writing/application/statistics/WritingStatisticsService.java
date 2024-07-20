package net.likelion.dailytales.writing.application.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WritingStatisticsService {
    private final KeywordStatisticsSupport keywordStatisticsSupport;
    private final WritingsCountSupport writingsCountSupport;

    public List<MainKeywordDto> getMainKeywords(String userId, Integer size) {
        return keywordStatisticsSupport.getMainKeywords(userId, size);
    }

    public TotalWritingsPerMonthDto countTotalWritingsPerMonth(String userId, Year year) {
        Map<Month, Integer> countingResult = writingsCountSupport.countTotalWritingsPerMonth(userId, year);
        for (Month month : Month.values()) {
            countingResult.putIfAbsent(month, 0);
        }
        int totalWritingsOfYear = countingResult
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        return new TotalWritingsPerMonthDto(totalWritingsOfYear, countingResult);
    }

    public TotalWritingsPerDayDto countTotalWritingsPerDay(String userId, Year year, Month month) {
        Map<Integer, Integer> countingResult = writingsCountSupport.countTotalWritingsPerDay(userId, year, month);
        for (int day = 1; day <= month.length(year.isLeap()); day++) {
            countingResult.putIfAbsent(day, 0);
        }
        return new TotalWritingsPerDayDto(countingResult.values().stream().toList());
    }

    public Long countPublishedWritings(String userId) {
        return writingsCountSupport.countPublishedWritings(userId);
    }
}
