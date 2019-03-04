package Users;

public abstract class User {
    private static String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}