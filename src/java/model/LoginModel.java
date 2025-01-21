package src.java.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class LoginModel {

    @Id
    @Column(name = "user_id")
    private Long id;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "password", nullable = false)
    private String password;

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() { return id; }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }
}