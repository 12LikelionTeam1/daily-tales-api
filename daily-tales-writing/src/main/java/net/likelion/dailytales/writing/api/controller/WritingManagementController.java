package net.likelion.dailytales.writing.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.likelion.dailytales.core.domain.authentication.LoggedInUser;
import net.likelion.dailytales.writing.api.dto.request.RegisterWritingRequest;
import net.likelion.dailytales.writing.api.dto.request.UpdateWritingCommentaryRequest;
import net.likelion.dailytales.writing.api.dto.request.UpdateWritingVisibilityRequest;
import net.likelion.dailytales.writing.api.dto.response.GetMyWritingsResponse;
import net.likelion.dailytales.writing.application.SimpleWritingDto;
import net.likelion.dailytales.writing.application.WritingManagementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static net.likelion.dailytales.writing.api.DateValidationUtil.validateDate;
import static net.likelion.dailytales.writing.api.WritingRequestMapper.preRegisterWriting;

@Slf4j
@RestController
@RequestMapping("/api/me/writings")
@RequiredArgsConstructor
public class WritingManagementController {
    private final WritingManagementService writingManagementService;

    @PostMapping
    public void registerWriting(
            @LoggedInUser String userId,
            @RequestBody RegisterWritingRequest request
    ) {
        writingManagementService.registerWriting(preRegisterWriting(userId, request));
    }

    @PatchMapping("/{writing-id}/commentary")
    public void updateCommentary(
            @LoggedInUser String userId,
            @RequestBody UpdateWritingCommentaryRequest request,
            @PathVariable("writing-id") String writingId
    ) {
        writingManagementService.updateCommentary(userId, writingId, request.commentary());
    }

    @PatchMapping("/{writing-id}/visibility")
    public void updateVisibility(
            @LoggedInUser String userId,
            @PathVariable("writing-id") String writingId,
            @RequestBody UpdateWritingVisibilityRequest request
    ) {
        writingManagementService.updateVisibility(userId, writingId, request.visibility());
    }

    @GetMapping
    public GetMyWritingsResponse getWritings(
            @LoggedInUser String userId,
            @RequestParam(name = "start-date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "end-date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        LocalDate today = LocalDate.now();
        if (startDate == null) {
            startDate = today;
        }
        if (endDate == null) {
            endDate = today;
        }
        validateDate(startDate, endDate);
        List<SimpleWritingDto> writings = writingManagementService.getWritingsOfUser(userId, startDate, endDate);
        return GetMyWritingsResponse.from(writings);
    }
}
