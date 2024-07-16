package net.likelion.dailytales.user.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.LoggedInUser;
import net.likelion.dailytales.core.domain.user.User;
import net.likelion.dailytales.user.api.dto.request.UpdateDisplayIdRequest;
import net.likelion.dailytales.user.api.dto.response.MeResponse;
import net.likelion.dailytales.user.application.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/me")
public class MeController {
    private final UserService userService;

    @GetMapping
    public MeResponse getMe(@LoggedInUser String userId) {
        User me = userService.getUserById(userId);

        return MeResponse.of(me);
    }

    @PatchMapping("/display-id")
    public void updateDisplayId(
            @LoggedInUser String userId,
            @RequestBody UpdateDisplayIdRequest request
    ) {
        userService.updateDisplayId(userId, request.displayId());
    }

    @PatchMapping("/nickname")
    public void updateNickname(
            @LoggedInUser String userId,
            @RequestBody UpdateDisplayIdRequest request
    ) {
        userService.updateNickname(userId, request.displayId());
    }
}
