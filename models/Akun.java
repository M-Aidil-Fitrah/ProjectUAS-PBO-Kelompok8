package models;

public abstract class Akun {
    protected String id;
    protected String username;
    protected String password;

    public Akun(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public abstract String getRole();

    // Getter untuk id
    public String getId() {
        return id;
    }

    // Getter untuk username
    public String getUsername() {
        return username;
    }

    // Getter untuk password
    public String getPassword() {
       return password;
    }
}
