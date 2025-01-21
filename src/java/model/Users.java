package src.java.model;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column(name = "user_name", nullable = false)
    private String user_name;
    @Column(name = "username")
    private String username;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "admin")
    private Boolean admin;
    @Column(name = "is_admin", nullable = false)
    private boolean is_admin = false;


    public Integer getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
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
        return is_admin;
    }


    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
        this.is_admin = is_admin;
    }
}
