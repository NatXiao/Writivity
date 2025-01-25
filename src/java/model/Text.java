package src.java.model;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "text_p")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_id", nullable = false)
    private Integer textId;
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "challenge_id", nullable = false)
    private Integer themeId;
    private String text_title;
    @Column(name = "body", nullable = false)
    private String body;
    @Column(name = "status")
    private String status;
    @Column(name = "text_submit")
    private Boolean textSubmit;
    @Column(name = "submitted_at")
    private Timestamp submittedAt;
    @Column(name = "reported")
    private Boolean reported;
    @Column(name = "disqualified")
    private Boolean disqualified;

    public Integer getTextId() {
        return textId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public String getText_title() {
        return text_title;
    }

    public String getBody() {
        return body;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getTextSubmit() {
        return textSubmit;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public Boolean getReported() {
        return reported;
    }

    public Boolean getDisqualified() {
        return disqualified;
    }

    public void setTextId(Integer text_id) {
        this.textId = text_id;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

    public void setThemeId(Integer theme_id) {
        this.themeId = theme_id;
    }

    public void setText_title(String text_title) {
        this.text_title = text_title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTextSubmit(Boolean text_submit) {
        this.textSubmit = text_submit;
    }

    public void setSubmittedAt(Timestamp submitted_at) {
        this.submittedAt = submitted_at;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public void setDisqualified(Boolean disqualified) {
        this.disqualified = disqualified;
    }
}
