package net.likelion.dailytales.writing.application;

import java.time.LocalDate;
import java.util.List;

public interface WritingSearchSupport {

    List<SimpleWritingDto> getWritingsOfUser(
            String userId,
            LocalDate startDate,
            LocalDate endDate
    );

}
