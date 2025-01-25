package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import src.java.model.Vote;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByVoteId(Integer voteId);
    Optional<Vote> findByUserId(Integer userId);
    Optional<Vote> findByTextId(Integer textId);
    Optional<Vote> findByRating(Integer rating);
}
