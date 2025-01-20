package src.java.model;

import java.sql.Timestamp;

public class Text {
    private Integer text_id;
    private Integer user_id;
    private Integer theme_id;
    private String text_title;
    private String body;
    private String status;
    private Boolean text_submit;
    private Timestamp submitted_at;
    private Boolean reported;
    private Boolean disqualified;

    public Integer getText_id() {
        return text_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getTheme_id() {
        return theme_id;
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

    public Boolean getText_submit() {
        return text_submit;
    }

    public Timestamp getSubmitted_at() {
        return submitted_at;
    }

    public Boolean getReported() {
        return reported;
    }

    public Boolean getDisqualified() {
        return disqualified;
    }

    public void setText_id(Integer text_id) {
        this.text_id = text_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
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

    public void setText_submit(Boolean text_submit) {
        this.text_submit = text_submit;
    }

    public void setSubmitted_at(Timestamp submitted_at) {
        this.submitted_at = submitted_at;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public void setDisqualified(Boolean disqualified) {
        this.disqualified = disqualified;
    }
}
