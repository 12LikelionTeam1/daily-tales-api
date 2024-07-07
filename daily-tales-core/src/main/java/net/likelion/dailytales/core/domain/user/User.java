package net.likelion.dailytales.core.domain.user;

public class User {
    private final String nickname;
    private final String displayId;

    public User(String nickname, String displayId) {
        this.nickname = nickname;
        this.displayId = displayId;
    }

    public String nickname() {
        return nickname;
    }

    public String displayId() {
        return displayId;
    }
}