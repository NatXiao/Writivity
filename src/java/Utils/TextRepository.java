package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import src.java.model.Text;

import java.util.List;
import java.util.Optional;

@Repository
public interface TextRepository extends JpaRepository<Text, Integer> {

    @Query("SELECT t FROM Text t JOIN FETCH t.user u WHERE t.challenge.challenge_id = :challengeId AND t.text_submit = true")
    List<Text> findTextsByChallengeId(Integer challengeId);

    // Nouvelle requête pour récupérer un texte par son ID et le challenge_id
    @Query("SELECT t FROM Text t JOIN FETCH t.user u WHERE t.challenge.challenge_id = :challengeId AND t.text_id = :textId AND t.text_submit = true")
    Optional<Text> findTextByChallengeIdAndTextId(Integer challengeId, Integer textId);


}

