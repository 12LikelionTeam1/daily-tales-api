package net.likelion.dailytales.user.infrastructure.persistence.repository.jpa;

import net.likelion.dailytales.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByDisplayId(String displayId);

    boolean existsByDisplayId(String displayId);

}
