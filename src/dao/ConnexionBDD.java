package dao; 

/**
 Classe utilitaire pour gérer la connexion à la base de données MySQL.
 Fournit une méthode pour obtenir la connexion et une autre pour la fermer.
 */


// importation des classes nécessaires
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD { // classe connexionbdd

    // informations pour se connecter à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/javaproject"; // url bdd
    private static final String UTILISATEUR = "root"; // utilisateur
    private static final String MOT_DE_PASSE = ""; // mot de passe (vide)

    private static Connection connexion; // objet connexion partagé

    /**
     * Méthode pour obtenir une connexion à la base de données.
     * Si la connexion est déjà ouverte, elle la retourne.
     * Sinon, elle en crée une nouvelle.
     *
     * @return la connexion à la base de données
     * @throws SQLException si une erreur se produit lors de la connexion
     */
    public static Connection getConnexion() throws SQLException {
        if (connexion == null || connexion.isClosed()) { // si pas connecté ou connexion fermée
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // charger le driver jdbc
                connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE); // établir la connexion
                System.out.println("Connexion à la base de données réussie."); // message succès
            } catch (ClassNotFoundException e) { // erreur si driver pas trouvé
                System.err.println("Pilote JDBC introuvable.");
                e.printStackTrace();
            } catch (SQLException e) { // erreur connexion
                System.err.println("Erreur de connexion à la base de données.");
                throw e;
            }
        }
        return connexion; // retourner la connexion
    }

    /**
     * Méthode pour fermer la connexion à la base de données.
     * Elle vérifie d'abord si la connexion est ouverte avant de la fermer.
     */
    public static void fermerConnexion() {
        try {
            if (connexion != null && !connexion.isClosed()) { // si connexion ouverte
                connexion.close(); // fermer
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) { // erreur fermeture
            System.err.println("Erreur lors de la fermeture de la connexion.");
            e.printStackTrace();
        }
    }
}
