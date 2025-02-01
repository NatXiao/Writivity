package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import src.java.model.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserId(Integer userId);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByMail(String mail);
}
