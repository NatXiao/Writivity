package src.java.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "theme")  // Spécifie que cette entité est mappée à la table 'theme'
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theme_id;

    @Column(nullable = false)
    private String theme_name;

    @Column
    private String conditions;

    @Column
    private Integer word_limit;

    @Column(nullable = false)
    private LocalDateTime open_at;

    @Column(nullable = false)
    private LocalDateTime close_at;

    // Getters et setters
    public Integer getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(Integer theme_id) {
        this.theme_id = theme_id;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Integer getWord_limit() {
        return word_limit;
    }

    public void setWord_limit(Integer word_limit) {
        this.word_limit = word_limit;
    }

    public LocalDateTime getOpen_at() {
        return open_at;
    }

    public void setOpen_at(LocalDateTime open_at) {
        this.open_at = open_at;
    }

    public LocalDateTime getClose_at() {
        return close_at;
    }

    public void setClose_at(LocalDateTime close_at) {
        this.close_at = close_at;
    }
}
