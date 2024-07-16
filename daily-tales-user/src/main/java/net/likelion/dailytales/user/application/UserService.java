package net.likelion.dailytales.user.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.user.User;
import net.likelion.dailytales.core.domain.user.UserRepository;
import net.likelion.dailytales.core.domain.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User getUserById(String userId) {
        if (!userRepository.exists(userId)) {
            throw new UserNotFoundException();
        }
        return userRepository.findById(userId);
    }

    @Transactional
    public void updateDisplayId(String userId, String displayId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        User updatedUser = includeDisplayIdPatch(user, displayId);
        userRepository.save(updatedUser);
    }

    @Transactional
    public void updateNickname(String userId, String nickname) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        User updatedUser = includeNicknamePatch(user, nickname);
        userRepository.save(updatedUser);
    }

    private static User includeNicknamePatch(User user, String nickname) {
        return new User(
                user.id(),
                nickname,
                user.displayId(),
                user.profileImageUrl()
        );
    }

    private static User includeDisplayIdPatch(User user, String displayId) {
        return new User(
                user.id(),
                user.nickname(),
                displayId,
                user.profileImageUrl()
        );
    }
}
