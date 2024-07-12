package net.likelion.dailytales.common.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.likelion.dailytales.common.BaseAuditEntity;
import net.likelion.dailytales.core.domain.user.User;

@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseAuditEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "display_id", unique = true, nullable = false)
    private String displayId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    public User toDomain() {
        return new User(id, nickname, displayId);
    }

    public static UserEntity of(User user) {
        return new UserEntity(user.id(), user.displayId(), user.nickname());
    }
}
