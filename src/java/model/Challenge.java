package src.java.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "challenge")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id", nullable = false)
    private Integer themeId;
    @Column(name = "challenge_name")
    private String themeName;
    @Column(name = "conditions")
    private String conditions;
    @Column(name = "word_limit", nullable = false)
    private Integer wordLimit;
    @Column(name = "open_at")
    private LocalDate openAt;
    @Column(name = "close_at")
    private LocalDate closeAt;

    public LocalDate getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(LocalDate closeAt) {
        this.closeAt = closeAt;
    }

    public LocalDate getOpenAt() {
        return openAt;
    }

    public void setOpenAt(LocalDate openAt) {
        this.openAt = openAt;
    }

    public Integer getWordLimit() {
        return wordLimit;
    }

    public void setWordLimit(Integer wordLimit) {
        this.wordLimit = wordLimit;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }
}
