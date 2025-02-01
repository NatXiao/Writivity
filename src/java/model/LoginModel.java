package src.java.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class LoginModel {

    @Id
    @Column(name = "user_id")
    private Long id;
    @Column(name = "mail", nullable = false)
    private String identifiant;
    @Column(name = "password", nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}