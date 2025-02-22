package src.java.Utils;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.java.model.Text;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface TextRepository extends JpaRepository<Text, Integer> {
    //Optional<Text> findByText_title(String text_title);
    //Optional<Text> findByBody(String body);
    Optional<Text> findByStatus(String status);

    //Optional<Text> findByText_submit(Boolean text_submit);
    //Optional<Text> findBySubmittedAt(Timestamp submitted_at);
    Optional<Text> findByReportedTrue();


    @Modifying
    @Transactional
    @Query("update Text t set t.disqualified = :disq WHERE t.textId = :textId")
    public void UpdateDisqualification(@Param("textId") int id, @Param("disq") Boolean disqualification);



    @Query("SELECT t FROM Text t JOIN FETCH t.user u WHERE t.challenge.challengeId = :challengeId AND t.textSubmit = true AND t.disqualified = FALSE ")
    List<Text> findTextsByChallengeId(Integer challengeId);

    // Nouvelle requête pour récupérer un texte par son ID et le challenge_id
    @Query("SELECT t FROM Text t JOIN FETCH t.user u WHERE t.challenge.challengeId = :challengeId AND t.textId = :textId AND t.textSubmit = true")
    Optional<Text> findTextByChallengeIdAndTextId(Integer challengeId, Integer textId);


}
