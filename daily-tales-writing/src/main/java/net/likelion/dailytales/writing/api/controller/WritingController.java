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
import org.springframework.web.bind.annotation.*;

import static net.likelion.dailytales.core.global.PageValidator.validatePageRequest;

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
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        validatePageRequest(page, size);
        PagedSimpleWritingDto writings = writingService.getWritingsExcludingUser(
                userId,
                page,
                size
        );
        return PagedSimpleWritingResponse.of(writings);
    }
}
