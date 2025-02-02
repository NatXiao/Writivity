package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import src.java.model.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUserId(Integer userId);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByMail(String mail);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.isAdmin = true WHERE u.userId = :userId")
    void setAdmin(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.isAdmin = false WHERE u.userId = :userId")
    void unsetAdmin(@Param("userId") Integer userId);
}
