package src.java.model;
import jakarta.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "pseudo", nullable = false)
    private String pseudo;
    @Column(nullable = false)
    private String mail;
    @Column(nullable = false)
    private String password;
    @Column(name = "isadmin", nullable = false)
    private boolean isAdmin = false;


    public Integer getUser_id() {
        return user_id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }


    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(Boolean admin) {
        this.isAdmin = admin;
    }
}
