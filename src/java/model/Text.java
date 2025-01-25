package src.java.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;  // Ajout de l'import pour List

@Entity
@Table(name = "text_p")  // Spécifie que cette entité est mappée à la table 'text_p'
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer textId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false) // Relation correcte avec Challenge
    private Challenge challenge;

    @Column(nullable = false)
    private String textTitle;

    @Column(nullable = false)
    private String body;

    @Column
    private String status;

    @Column
    private Boolean textSubmit;

    @Column
    private Timestamp submittedAt;

    @Column
    private Boolean reported;

    @Column
    private Boolean disqualified;

    // Nouvelle relation ajoutée ici
    @OneToMany(mappedBy = "text", fetch = FetchType.LAZY)
    private List<Comment> comments;

    // Getters et setters pour comments
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    // Autres getters et setters


    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
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

    public String getTexTitle() {
        return textTitle;
    }

    public void setTexTitle(String textTitle) {
        this.textTitle = textTitle;
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

    public Boolean getTextSubmit() {
        return textSubmit;
    }

    public void setTextSubmit(Boolean textSubmit) {
        this.textSubmit = textSubmit;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
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
