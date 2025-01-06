package src.java.model;

import java.sql.Timestamp;

public class Text {
    private Integer text_id;
    private Integer user_id;
    private Integer theme_id;
    private String body;
    private String status;
    private Boolean text_submit;
    private Timestamp submitted_at;
    private Boolean reported;
    private Boolean disqualified;
}
