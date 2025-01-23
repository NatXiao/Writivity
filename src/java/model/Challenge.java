package src.java.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer challenge_id;

    @Column(nullable = false)
    private String challenge_name;

    @Column(nullable = false)
    private Integer word_limit;

    @Column(nullable = false)
    private LocalDateTime openAt;

    @Column(nullable = false)
    private LocalDateTime closeAt;

    @Column
    private String conditions;

    // Getters et setters
    public Integer getChallenge_id() {
        return challenge_id;
    }

    public void setChallenge_id(Integer challenge_id) {
        this.challenge_id = challenge_id;
    }

    public String getChallenge_name() {
        return challenge_name;
    }

    public void setChallenge_name(String challenge_name) {
        this.challenge_name = challenge_name;
    }

    public Integer getWord_limit() {
        return word_limit;
    }

    public void setWord_limit(Integer word_limit) {
        this.word_limit = word_limit;
    }

    public LocalDateTime getOpenAt() {
        return openAt;
    }

    public void setOpenAt(LocalDateTime openAt) {
        this.openAt = openAt;
    }

    public LocalDateTime getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(LocalDateTime closeAt) {
        this.closeAt = closeAt;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}