package net.likelion.dailytales.writing.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingSearchSupport writingSearchSupport;

    public PagedSimpleWritingDto getWritingsExcludingUser(
            String userId,
            int page,
            int size
    ) {
        return writingSearchSupport.getWritingsExcludingUser(
                userId,
                page,
                size
        );
    }
}
