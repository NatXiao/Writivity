package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.java.model.Feedback;
import src.java.model.Users;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
   // List<Feedback> findByUser(Users user); // Find feedback by a specific user
}