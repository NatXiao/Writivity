package src.java.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")  // Spécifie que cette entité est mappée à la table 'comment'
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

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
    private Timestamp created_at;

    @Column
    private Boolean deleted;

    // Getters et setters
    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
