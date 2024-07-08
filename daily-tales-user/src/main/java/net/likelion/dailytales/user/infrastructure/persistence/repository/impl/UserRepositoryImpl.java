package net.likelion.dailytales.user.infrastructure.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.user.User;
import net.likelion.dailytales.core.domain.user.UserRepository;
import net.likelion.dailytales.user.infrastructure.persistence.entity.UserEntity;
import net.likelion.dailytales.user.infrastructure.persistence.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public void save(String id, User user) {
        jpaUserRepository.save(UserEntity.of(id, user));
    }

    @Override
    public boolean exists(String id) {
        return jpaUserRepository.existsById(id);
    }

    @Override
    public boolean existsByDisplayId(String displayId) {
        return jpaUserRepository.existsByDisplayId(displayId);
    }

    @Override
    public User findById(String id) {
        return jpaUserRepository
                .findById(id)
                .map(UserEntity::toDomain)
                .orElse(null);
    }

    @Override
    public User findByDisplayId(String displayId) {
        return jpaUserRepository
                .findByDisplayId(displayId)
                .map(UserEntity::toDomain)
                .orElse(null);
    }
}
