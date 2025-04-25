package dao;

import java.sql.*;

public class UserDAO {

    public static class Utilisateur {
        public int id;
        public String role;

        public Utilisateur(int id, String role) {
            this.id = id;
            this.role = role;
        }
    }

    public Utilisateur verifierConnexion(String email, String motDePasse) {
        String requete = "SELECT ID_User, Role FROM User WHERE email = ? AND MotDePasse = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setString(1, email);
            stmt.setString(2, motDePasse);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilisateur(rs.getInt("ID_User"), rs.getString("Role"));
            }

        } catch (SQLException e) {
            System.err.println("Erreur de vérification d'identifiants.");
            e.printStackTrace();
        }

        return null;
    }
}
