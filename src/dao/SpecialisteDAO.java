package dao;

import modele.Specialiste;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialisteDAO {

    public List<Specialiste> recupererTousLesSpecialistes() {
        List<Specialiste> liste = new ArrayList<>();
        String requete = "SELECT * FROM Specialiste";

        try (Connection conn = ConnexionBDD.getConnexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(requete)) {

            while (rs.next()) {
                Specialiste s = new Specialiste(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("specialite"),
                        rs.getString("qualification")
                );
                liste.add(s);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des spécialistes.");
            e.printStackTrace();
        }

        return liste;
    }

    public boolean ajouterSpecialiste(Specialiste s) {
        String requete = "INSERT INTO Specialiste (nom, prenom, email, specialite, qualification) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setString(1, s.getNom());
            stmt.setString(2, s.getPrenom());
            stmt.setString(3, s.getEmail());
            stmt.setString(4, s.getSpecialite());
            stmt.setString(5, s.getQualification());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du spécialiste.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerSpecialisteParId(int id) {
        String requete = "DELETE FROM Specialiste WHERE id = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du spécialiste.");
            e.printStackTrace();
            return false;
        }
    }
}
