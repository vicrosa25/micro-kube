package rosa.victor.microususer.service;

import rosa.victor.microususer.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
    User save(User user);
    void delete(Long id);
}
