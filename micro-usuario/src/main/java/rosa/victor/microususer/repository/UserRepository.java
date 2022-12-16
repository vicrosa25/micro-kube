package rosa.victor.microususer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rosa.victor.microususer.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
