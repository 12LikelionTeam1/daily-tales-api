package net.likelion.dailytales.writing.api;

import net.likelion.dailytales.core.domain.writing.WritingCommentary;
import net.likelion.dailytales.writing.api.dto.request.RegisterWritingRequest;
import net.likelion.dailytales.writing.application.PreRegisterWritingDto;

public class WritingRequestMapper {
    public static PreRegisterWritingDto preRegisterWriting(
            String writerId,
            RegisterWritingRequest request
    ) {
        return new PreRegisterWritingDto(
                writerId,
                request.title(),
                request.keywords(),
                request.content(),
                WritingCommentary.of(request.commentary())
        );
    }
}
