package dao;

// importation des classes nécessaires
import java.sql.Connection;
import java.sql.SQLException;

import static dao.ConnexionBDD.fermerConnexion; // importer méthode fermer connexion
import static dao.ConnexionBDD.getConnexion;    // importer méthode obtenir connexion
/**
 Classe simple pour tester la connexion à la base de données.
 Exécute un test d'ouverture et de fermeture de connexion.
 */
public class DBConnectionTest { // classe pour tester la connexion
    /**
     * Méthode principale pour tester la connexion à la base de données.
     * Elle essaie d'établir une connexion et de la fermer proprement.
     *
     * @param args arguments de la ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) { // méthode principale
        try {
            // établir la connexion
            Connection conn = getConnexion();

            // ici on pourrait faire d'autres requêtes pour tester

            // fermer la connexion proprement
            fermerConnexion();
        } catch (SQLException e) { // si une erreur SQL survient
            System.err.println("Une erreur est survenue : " + e.getMessage());
        }
    }
}
