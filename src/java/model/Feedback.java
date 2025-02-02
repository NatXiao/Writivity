package src.java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false)
    private Integer feedback_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false) // Join with Users table
    private Users user;

    @Column(name = "feedback_body", nullable = false)
    private String feedback_body;

    public void setFeedback_id(Integer feedback_id) {
        this.feedback_id = feedback_id;
    }

    public void setFeedback_body(String feedback_body) {
        this.feedback_body = feedback_body;
    }

    public Integer getFeedback_id() {
        return feedback_id;
    }

    public String getFeedback_body() {
        return feedback_body;
    }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

}
