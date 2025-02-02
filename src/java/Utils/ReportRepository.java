package src.java.Utils;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import src.java.model.Rate;
import src.java.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
