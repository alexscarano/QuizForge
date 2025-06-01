package util;

public class DBCredentials {
    // Altere conforme credenciais do seu bd
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quizforge";
    private static final String DB_USER = "root";
    private static final String DB_USER_PASSWORD = "";

    public static String getDBUrl() {
        return DB_URL;
    }

    public static String getDBUser() {
        return DB_USER;
    }

    public static String getDBUserPassword() {
        return DB_USER_PASSWORD;
    }
       
}


