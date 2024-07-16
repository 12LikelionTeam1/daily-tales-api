package net.likelion.dailytales.writing.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.common.PagedResponse;
import net.likelion.dailytales.core.domain.authentication.LoggedInUser;
import net.likelion.dailytales.core.domain.writing.Writing;
import net.likelion.dailytales.writing.api.dto.response.PagedSimpleWritingResponse;
import net.likelion.dailytales.writing.api.dto.response.SimpleWritingResponse;
import net.likelion.dailytales.writing.api.dto.response.WritingResponse;
import net.likelion.dailytales.writing.application.PagedSimpleWritingDto;
import net.likelion.dailytales.writing.application.WritingManagementService;
import net.likelion.dailytales.writing.application.WritingService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/writings")
@RequiredArgsConstructor
public class WritingController {
    private final WritingManagementService writingManagementService;
    private final WritingService writingService;

    @GetMapping("/{writing-id}")
    public WritingResponse getWriting(
            @LoggedInUser String userId,
            @PathVariable("writing-id") String writingId
    ) {
        Writing writing = writingManagementService.getWriting(userId, writingId);

        return WritingResponse.from(writing);
    }


    @GetMapping
    public PagedResponse<SimpleWritingResponse> getWritingsOfOtherUser(
            @LoggedInUser String userId,
            @PageableDefault Pageable pageable
    ) {
        PagedSimpleWritingDto writings = writingService.getWritingsExcludingUser(
                userId,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
        return PagedSimpleWritingResponse.of(writings);
    }
}
