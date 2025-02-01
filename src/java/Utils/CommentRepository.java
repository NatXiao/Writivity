package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import src.java.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommentId(Integer comment_id);
    List<Comment> findByBody(String body);
    List<Comment> findByReported(Boolean reported);
}
