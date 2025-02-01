package src.java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id", nullable = false)
    private Integer rateId;
    @Column(name = "text_id", nullable = false)
    private Integer textId;
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "rate", nullable = false)
    private Integer rate;

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
