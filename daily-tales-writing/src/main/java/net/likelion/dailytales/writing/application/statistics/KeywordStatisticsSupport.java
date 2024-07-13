package net.likelion.dailytales.writing.application.statistics;

import java.util.List;

public interface KeywordStatisticsSupport {

    List<MainKeywordDto> getMainKeywords(String userId, Integer size);

}
