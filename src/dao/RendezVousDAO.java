package dao;

import modele.RendezVous;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {

    // Ajouter un nouveau rendez-vous
    public boolean ajouterRendezVous(RendezVous r) {
        String requete = "INSERT INTO RendezVous (idPatient, idSpecialiste, idLieu, date, heure, note) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, r.getIdPatient());
            stmt.setInt(2, r.getIdSpecialiste());
            stmt.setInt(3, r.getIdLieu());
            stmt.setString(4, r.getDate());
            stmt.setString(5, r.getHeure());
            stmt.setString(6, r.getNote());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du rendez-vous.");
            e.printStackTrace();
            return false;
        }
    }

    // Récupérer tous les RDV pour un patient
    public List<RendezVous> recupererRendezVousParPatient(int idPatient) {
        List<RendezVous> liste = new ArrayList<>();
        String requete = "SELECT * FROM RendezVous WHERE idPatient = ? ORDER BY date DESC, heure DESC";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idPatient);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RendezVous r = new RendezVous(
                        rs.getInt("id"),
                        rs.getInt("idPatient"),
                        rs.getInt("idSpecialiste"),
                        rs.getInt("idLieu"),
                        rs.getString("date"),
                        rs.getString("heure"),
                        rs.getString("note")
                );
                liste.add(r);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des RDV.");
            e.printStackTrace();
        }

        return liste;
    }

    // (Optionnel) Lister tous les rendez-vous (admin)
    public List<RendezVous> recupererTousLesRendezVous() {
        List<RendezVous> liste = new ArrayList<>();
        String requete = "SELECT * FROM RendezVous ORDER BY date DESC, heure DESC";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RendezVous r = new RendezVous(
                        rs.getInt("id"),
                        rs.getInt("idPatient"),
                        rs.getInt("idSpecialiste"),
                        rs.getInt("idLieu"),
                        rs.getString("date"),
                        rs.getString("heure"),
                        rs.getString("note")
                );
                liste.add(r);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des RDV (admin).");
            e.printStackTrace();
        }

        return liste;
    }

    // (Optionnel) Supprimer un rendez-vous par ID
    public boolean supprimerRendezVousParId(int id) {
        String requete = "DELETE FROM RendezVous WHERE id = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du RDV.");
            e.printStackTrace();
            return false;
        }
    }
}
