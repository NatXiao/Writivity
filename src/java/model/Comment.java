package src.java.model;

public class Comment {
    private Integer comment_id;
    private Integer text_id;
    private Integer user_id;
    private String body;
    private Boolean reported;

    public Integer getComment_id() {
        return comment_id;
    }

    public Integer getText_id() {
        return text_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getBody() {
        return body;
    }

    public Boolean getReported() {
        return reported;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public void setText_id(Integer text_id) {
        this.text_id = text_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }
}
