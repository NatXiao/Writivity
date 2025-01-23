package src.java.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import src.java.model.Challenge;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

    // Challenges en cours
    @Query("SELECT c FROM Challenge c WHERE c.openAt <= :currentDate AND c.closeAt >= :currentDate")
    List<Challenge> findCurrentChallenges(LocalDateTime currentDate);

    // Challenges passés
    @Query("SELECT c FROM Challenge c WHERE c.closeAt < :currentDate")
    List<Challenge> findOldChallenges(LocalDateTime currentDate);
}
