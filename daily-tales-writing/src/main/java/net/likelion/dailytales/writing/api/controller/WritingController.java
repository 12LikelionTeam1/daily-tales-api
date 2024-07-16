package net.likelion.dailytales.writing.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.LoggedInUser;
import net.likelion.dailytales.writing.api.dto.request.RegisterWritingCollectionRequest;
import net.likelion.dailytales.writing.api.dto.response.GetWritingCollectionListResponse;
import net.likelion.dailytales.writing.application.WritingManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/writings")
@RequiredArgsConstructor
public class WritingController {

    private final WritingManagementService writingManagementService;


    @PostMapping
    public void saveWriting(
            @LoggedInUser String userId,
            @RequestBody RegisterWritingCollectionRequest registerWritingCollectionRequest
            ){
        writingManagementService.registerWritingCollection(registerWritingCollectionRequest,userId);
    }

    @GetMapping
    public List<GetWritingCollectionListResponse> getWritingCollectionList(
            @LoggedInUser String userId)
    {
        return writingManagementService.getWritingCollectionList(userId);
    }


}
