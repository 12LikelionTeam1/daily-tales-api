package net.likelion.dailytales.core.domain.user;

public interface UserRepository {

    void save(String id, User user);

    User findById(String id);

    User findByDisplayId(String displayId);

}
