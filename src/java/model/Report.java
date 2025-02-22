package src.java.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Integer reportId;
    //@Column(name = "text_id", nullable = false)
    //private Integer textId;
    @Column(name = "reporter_id", nullable = false)
    private Integer reporterId;
    @Column(name = "problem", nullable = false)
    private String problem;



    @ManyToOne
    @JoinColumn(name = "text_id", nullable = false)
    private Text text;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }



    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /*public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }*/

    public Integer getReporterId() {
        return reporterId;
    }

    public void setReporterId(Integer reporterId) {
        this.reporterId = reporterId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
