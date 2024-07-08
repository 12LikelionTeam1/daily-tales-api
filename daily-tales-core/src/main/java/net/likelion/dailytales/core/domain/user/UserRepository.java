package net.likelion.dailytales.core.domain.user;

public interface UserRepository {

    void save(String id, User user);

    boolean exists(String id);

    boolean existsByDisplayId(String displayId);

    User findById(String id);

    User findByDisplayId(String displayId);

}
