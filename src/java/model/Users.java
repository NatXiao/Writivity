package src.java.model;

public class Users {

    private Integer user_id;
    private String username;
    private String mail;
    private String password;
    private Boolean admin;


    public Integer getUser_id() {
        return user_id;
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

    public Boolean getAdmin() {
        return admin;
    }


    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
        this.admin = admin;
    }
}
