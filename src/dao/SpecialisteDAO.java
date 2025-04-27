package dao; // package dao

// importation des classes nécessaires
import modele.Specialiste;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialisteDAO { // classe d'accès aux données des spécialistes

    // méthode pour récupérer tous les spécialistes
    public List<Specialiste> recupererTousLesSpecialistes() {
        List<Specialiste> liste = new ArrayList<>();
        String requete = "SELECT * FROM specialiste"; // requête SQL

        try (Connection conn = ConnexionBDD.getConnexion(); // connexion
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(requete)) {

            while (rs.next()) { // parcourir les résultats
                Specialiste s = new Specialiste(
                        rs.getInt("ID_Spécialiste"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("spécialité")
                );
                liste.add(s); // ajouter à la liste
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des spécialistes.");
            e.printStackTrace();
        }

        return liste; // renvoyer la liste
    }

    // méthode pour ajouter un spécialiste
    public boolean ajouterSpecialiste(Specialiste s) {
        String requete = "INSERT INTO specialiste (nom, prenom, email, specialite) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setString(1, s.getNom());
            stmt.setString(2, s.getPrenom());
            stmt.setString(3, s.getEmail());
            stmt.setString(4, s.getSpecialite());

            return stmt.executeUpdate() > 0; // true si insertion réussie

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du spécialiste.");
            e.printStackTrace();
            return false;
        }
    }

    // méthode pour supprimer un spécialiste par son id
    public boolean supprimerSpecialisteParId(int id) {
        String requete = "DELETE FROM specialiste WHERE ID_Spécialiste = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0; // true si suppression réussie

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du spécialiste.");
            e.printStackTrace();
            return false;
        }
    }
}
