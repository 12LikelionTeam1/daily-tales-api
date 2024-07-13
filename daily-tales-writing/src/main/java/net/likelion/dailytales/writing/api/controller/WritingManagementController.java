package net.likelion.dailytales.writing.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.likelion.dailytales.core.domain.user.UserRepository;
import net.likelion.dailytales.core.global.exception.validation.InvalidDateArgumentException;
import net.likelion.dailytales.writing.api.dto.request.RegisterWritingRequest;
import net.likelion.dailytales.writing.api.dto.request.UpdateWritingCommentaryRequest;
import net.likelion.dailytales.writing.api.dto.request.UpdateWritingVisibilityRequest;
import net.likelion.dailytales.writing.api.dto.response.SimpleWritingsResponse;
import net.likelion.dailytales.writing.application.SimpleWritingInfo;
import net.likelion.dailytales.writing.application.WritingManagementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

import static net.likelion.dailytales.writing.api.WritingRequestMapper.preRegisterWriting;

@Slf4j
@RestController
@RequestMapping("/api/me/writings")
@RequiredArgsConstructor
public class WritingManagementController {
    private final WritingManagementService writingManagementService;
    private final UserRepository userRepository;

    @PostMapping
    public void registerWriting(
            @AuthenticationPrincipal String userId,
            @RequestBody RegisterWritingRequest request
    ) {
        writingManagementService.registerWriting(preRegisterWriting(userId, request));
    }

    @PatchMapping("/{id}/commentary")
    public void updateCommentary(
            @RequestBody UpdateWritingCommentaryRequest request,
            @PathVariable("id") String id
    ) {
        writingManagementService.updateCommentary(id, request.commentary());
    }

    @PatchMapping("/{id}/visibility")
    public void updateVisibility(
            @PathVariable("id") String id,
            @RequestBody UpdateWritingVisibilityRequest request
    ) {
        writingManagementService.updateVisibility(id, request.visibility());
    }

    @GetMapping
    public SimpleWritingsResponse getWritings(
            @AuthenticationPrincipal String userId,
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
        List<SimpleWritingInfo> writings = writingManagementService.getWritingsOfUser(userId, startDate, endDate);
        return SimpleWritingsResponse.from(writings);
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateArgumentException();
        }
    }
}
