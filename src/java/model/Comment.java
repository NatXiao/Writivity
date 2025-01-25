package src.java.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")  // Spécifie que cette entité est mappée à la table 'comment'
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "text_id", nullable = false)
    private Text text;  // Relation avec l'entité Text

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;  // Relation avec l'entité User

    @Column(nullable = false)
    private String body;

    @Column
    private Boolean reported;

    @Column
    private Timestamp createdAt;

    @Column
    private Boolean deleted;

    // Getters et setters


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getReported() {
        return reported;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
