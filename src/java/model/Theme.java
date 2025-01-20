package src.java.model;

import java.sql.Date;

public class Theme {
    private Integer theme_id;
    private String theme_name;
    private String conditions;
    private Integer word_limit;
    private Date open_at;
    private Date close_at;

    public Integer getTheme_id() {
        return theme_id;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public String getConditions() {
        return conditions;
    }

    public Integer getWord_limit() {
        return word_limit;
    }

    public Date getOpen_at() {
        return open_at;
    }

    public Date getClose_at() {
        return close_at;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setWord_limit(Integer word_limit) {
        this.word_limit = word_limit;
    }

    public void setOpen_at(Date open_at) {
        this.open_at = open_at;
    }

    public void setClose_at(Date close_at) {
        this.close_at = close_at;
    }
}
