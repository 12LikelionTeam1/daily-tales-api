package net.likelion.dailytales.writing.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.LoggedInUser;
import net.likelion.dailytales.core.domain.writing.Writing;
import net.likelion.dailytales.writing.api.dto.response.WritingResponse;
import net.likelion.dailytales.writing.application.WritingManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/writings")
@RequiredArgsConstructor
public class WritingController {
    private final WritingManagementService writingManagementService;

    @GetMapping("/{writing-id}")
    public WritingResponse getWriting(
            @LoggedInUser String userId,
            @PathVariable("writing-id") String writingId
    ) {
        Writing writing = writingManagementService.getWriting(userId, writingId);

        return WritingResponse.from(writing);
    }
}
