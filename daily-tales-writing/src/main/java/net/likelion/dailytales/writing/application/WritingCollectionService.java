package net.likelion.dailytales.writing.application;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.user.UserRepository;
import net.likelion.dailytales.core.domain.user.exception.UserNotFoundException;
import net.likelion.dailytales.core.domain.writing.Visibility;
import net.likelion.dailytales.core.domain.writing.WritingRepository;
import net.likelion.dailytales.core.domain.writing.exception.WritingNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingCollectionService {
    private final WritingCollectionRepository writingCollectionRepository;
    private final WritingRepository writingRepository;
    private final UserRepository userRepository;

    public void registerWriting(String userId, String writingId) {
        if (!userRepository.exists(userId)) {
            throw new UserNotFoundException();
        }
        if (!writingRepository.exists(writingId)) {
            throw new WritingNotFoundException();
        }
        Visibility visibility = writingRepository.getVisibilityById(writingId);
        String writerId = writingRepository.getWriterIdById(writingId);
        if (visibility == Visibility.PRIVATE && !writerId.equals(userId)) {
            throw new WritingNotFoundException();
        }
        writingCollectionRepository.save(userId, writingId);
    }

    public List<SimpleWritingDto> getCollections(String userId) {
        return writingCollectionRepository.findWritingsByUserId(userId);
    }

    public void deleteWriting(String userId, String writingId) {
        if (!userRepository.exists(userId)) {
            throw new UserNotFoundException();
        }
        if (!writingCollectionRepository.exists(userId, writingId)) {
            throw new WritingNotFoundException();
        }
        writingCollectionRepository.delete(userId, writingId);
    }
}
