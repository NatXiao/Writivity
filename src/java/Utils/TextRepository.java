package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import src.java.model.Text;

import java.security.Timestamp;
import java.util.Optional;

public interface TextRepository extends JpaRepository<Text, Long> {
    //Optional<Text> findByText_title(String text_title);
    //Optional<Text> findByBody(String body);
    Optional<Text> findByStatus(String status);
    //Optional<Text> findByText_submit(Boolean text_submit);
    Optional<Text> findBySubmittedAt(java.sql.Timestamp submittedAt);
    Optional<Text> findByReported(Boolean reported);
    Optional<Text> findByDisqualified(Boolean disqualified);
}
