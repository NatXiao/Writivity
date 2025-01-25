package src.java.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "text_p")  // Spécifie que cette entité est mappée à la table 'text_p'
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer text_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false) // Relation correcte avec Challenge
    private Challenge challenge;

    @Column(nullable = false)
    private String text_title;

    @Column(nullable = false)
    private String body;

    @Column
    private String status;

    @Column
    private Boolean text_submit;

    @Column
    private Timestamp submitted_at;

    @Column
    private Boolean reported;

    @Column
    private Boolean disqualified;

    // Getters et setters
    public Integer getText_id() {
        return text_id;
    }

    public void setText_id(Integer text_id) {
        this.text_id = text_id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public String getText_title() {
        return text_title;
    }

    public void setText_title(String text_title) {
        this.text_title = text_title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getText_submit() {
        return text_submit;
    }

    public void setText_submit(Boolean text_submit) {
        this.text_submit = text_submit;
    }

    public Timestamp getSubmitted_at() {
        return submitted_at;
    }

    public void setSubmitted_at(Timestamp submitted_at) {
        this.submitted_at = submitted_at;
    }

    public Boolean getReported() {
        return reported;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public Boolean getDisqualified() {
        return disqualified;
    }

    public void setDisqualified(Boolean disqualified) {
        this.disqualified = disqualified;
    }
}
