package dao; 

// importation des classes nécessaires
import java.sql.*;

public class UserDAO { // classe d'accès aux données pour l'utilisateur

    public static class Utilisateur { // classe interne pour stocker un utilisateur connecté
        public int id; // id de l'utilisateur
        public String role; // role (patient ou admin)

        public Utilisateur(int id, String role) { // constructeur
            this.id = id;
            this.role = role;
        }
    }

    // méthode pour vérifier les identifiants de connexion
    public Utilisateur verifierConnexion(String email, String motDePasse) {
        String requete = "SELECT ID_User, Role FROM User WHERE email = ? AND MotDePasse = ?"; // requête SQL

        try (Connection conn = ConnexionBDD.getConnexion(); // connexion à la base
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setString(1, email); // insertion des paramètres
            stmt.setString(2, motDePasse);

            ResultSet rs = stmt.executeQuery(); // exécution

            if (rs.next()) { // si un résultat trouvé
                return new Utilisateur(rs.getInt("ID_User"), rs.getString("Role")); // on retourne l'utilisateur
            }

        } catch (SQLException e) {
            System.err.println("Erreur de vérification d'identifiants.");
            e.printStackTrace();
        }

        return null; // si pas trouvé, on retourne null
    }
}
