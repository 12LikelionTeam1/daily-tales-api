package net.likelion.dailytales.writing.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.LoggedInUser;
import net.likelion.dailytales.writing.api.dto.request.RegisterWritingCollectionRequest;
import net.likelion.dailytales.writing.api.dto.response.WritingCollectionResponse;
import net.likelion.dailytales.writing.application.SimpleWritingDto;
import net.likelion.dailytales.writing.application.WritingCollectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/me/writing-collection")
@RequiredArgsConstructor
public class WritingCollectionController {
    private final WritingCollectionService writingCollectionService;

    @PostMapping
    public void registerWriting(
            @LoggedInUser String userId,
            @RequestBody RegisterWritingCollectionRequest request
    ){
        writingCollectionService.registerWriting(userId, request.writingId());
    }

    @GetMapping
    public WritingCollectionResponse getWritingCollection(@LoggedInUser String userId) {
        List<SimpleWritingDto> writingCollection = writingCollectionService.getCollections(userId);

        return WritingCollectionResponse.of(writingCollection);
    }

    @DeleteMapping("/{writing-id}")
    public void removeWriting(
            @LoggedInUser String userId,
            @PathVariable("writing-id") String writingId
    ) {
        writingCollectionService.removeWriting(userId, writingId);
    }
}
