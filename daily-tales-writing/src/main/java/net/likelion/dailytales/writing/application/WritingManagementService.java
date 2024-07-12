package net.likelion.dailytales.writing.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.user.User;
import net.likelion.dailytales.core.domain.user.UserRepository;
import net.likelion.dailytales.core.domain.user.exception.UserNotFoundException;
import net.likelion.dailytales.core.domain.writing.Visibility;
import net.likelion.dailytales.core.domain.writing.Writing;
import net.likelion.dailytales.core.domain.writing.WritingRepository;
import net.likelion.dailytales.core.domain.writing.exception.WritingNotFoundException;
import net.likelion.dailytales.core.global.IdentifierGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingManagementService {
    private final IdentifierGenerator identifierGenerator;
    private final WritingSearchSupport writingSearchSupport;
    private final UserRepository userRepository;
    private final WritingRepository writingRepository;

    public void registerWriting(PreRegisterWritingDto writing) {
        if (!userRepository.exists(writing.writerId())) {
            throw new UserNotFoundException();
        }
        User writer = userRepository.findById(writing.writerId());
        Writing newWriting = Writing.newWriting(
                identifierGenerator.generate(),
                writer,
                writing.title(),
                Visibility.PRIVATE,
                writing.keywords(),
                writing.content(),
                writing.commentary()
        );
        writingRepository.save(newWriting);
    }

    @Transactional
    public void updateCommentary(String id, String commentary) {
        if (writingRepository.notExists(id)) {
            throw new WritingNotFoundException();
        }
        writingRepository.updateCommentary(id, commentary);
    }

    @Transactional
    public void updateVisibility(String id, Visibility visibility) {
        if (writingRepository.notExists(id)) {
            throw new WritingNotFoundException();
        }
        writingRepository.updateVisibility(id, visibility);
    }

    public List<SimpleWritingInfo> getWritingsOfUser(
            String userId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return writingSearchSupport.getWritingsOfUser(userId, startDate, endDate);
    }
}