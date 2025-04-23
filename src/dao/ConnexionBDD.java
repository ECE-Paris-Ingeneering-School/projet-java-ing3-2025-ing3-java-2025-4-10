package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
    private static final String URL = "jdbc:mysql://localhost:3306/nom_de_votre_bdd";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    private static Connection connexion;

    public static Connection getConnexion() throws SQLException {
        if (connexion == null || connexion.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
                System.out.println("Connexion à la base de données réussie.");
            } catch (ClassNotFoundException e) {
                System.err.println("Pilote JDBC introuvable.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Erreur de connexion à la base de données.");
                throw e;
            }
        }
        return connexion;
    }

    public static void fermerConnexion() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion.");
            e.printStackTrace();
        }
    }
}
