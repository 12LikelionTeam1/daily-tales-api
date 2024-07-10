package net.likelion.dailytales.common.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.likelion.dailytales.core.domain.user.User;

@Entity(name = "`user`")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "display_id", unique = true, nullable = false)
    private String displayId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    public User toDomain() {
        return new User(nickname, displayId);
    }

    public static UserEntity of(String id, User user) {
        return new UserEntity(id, user.displayId(), user.nickname());
    }
}
