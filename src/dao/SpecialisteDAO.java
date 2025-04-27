package dao;

// importation des classes nécessaires
import modele.Specialiste;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe d'accès aux données des spécialistes.
 * Permet de récupérer, ajouter et supprimer des spécialistes dans la base de données.
 */
public class SpecialisteDAO { // classe d'accès aux données des spécialistes

    /**
     * Méthode pour récupérer tous les spécialistes de la base de données.
     * @return une liste de spécialistes.
     */
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

    /**
     * Méthode pour ajouter un spécialiste dans la base de données.
     * @param s le spécialiste à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
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

    /**
     * Méthode pour supprimer un spécialiste de la base de données par son ID.
     * @param id l'ID du spécialiste à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
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
