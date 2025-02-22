package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import src.java.model.Challenge;

import java.time.LocalDate;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Optional<Challenge> findByConditions(String conditions);

    Optional<Challenge> findByWordLimit(Integer wordLimit);

    Optional<Challenge> findByOpenAt(LocalDate openAt);

    Optional<Challenge> findByCloseAt(LocalDate closeAt);

    Challenge findByChallengeId(Integer challengeId);

    Optional<Challenge> findByChallengeName(String challengeName);

}
