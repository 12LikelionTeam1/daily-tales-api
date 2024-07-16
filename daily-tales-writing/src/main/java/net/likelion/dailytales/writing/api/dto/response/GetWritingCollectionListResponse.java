package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

public record GetWritingCollectionListResponse
        (
                String writingId,
                String title,
                String userNickname,
                String profileUrl,
                @JsonFormat(pattern = "yyyy-MM-dd") LocalDateTime writtenAt

        ){

}
