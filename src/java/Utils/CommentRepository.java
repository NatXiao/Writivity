package src.java.Utils;

import org.springframework.data.jpa.repository.JpaRepository;
import src.java.model.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentId(Integer commentId);
    Optional<Comment> findByTextId(Integer textId);
    Optional<Comment> findByUserId(Integer userId);
    Optional<Comment> findByBody(String body);
    Optional<Comment> findByReported(Boolean reported);
}
