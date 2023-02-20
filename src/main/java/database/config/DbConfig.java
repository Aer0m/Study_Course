package database.config;

public class DbConfig {
    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    private final static String url = "jdbc:postgresql://localhost:5432/users";
    private final static String user = "postgres";
    private final static String password = "123";
}