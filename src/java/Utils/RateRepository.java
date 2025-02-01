package src.java.Utils;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import src.java.model.Rate;

import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByRateId(Integer rateId);
    List<Rate> findByUserId(Integer userId);
    List<Rate> findByTextId(Integer textId);
    List<Rate> findByRate(Integer rate);
    List<Rate> findByTextIdAndUserId(Integer textId, Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE Rate SET rate = :newRate WHERE rateId = :rateId")
    void updateRateByRateId(Integer newRate, Integer rateId);
}
