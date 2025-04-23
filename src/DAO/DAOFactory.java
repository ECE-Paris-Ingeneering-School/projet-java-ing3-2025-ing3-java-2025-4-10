package DAO;

public class DAOFactory {
    private static String url;
    private String username;
    private String password;

    //Constructeur
    public DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

}


