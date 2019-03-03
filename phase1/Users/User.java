package Users;

public abstract class User {
    private String username, password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}