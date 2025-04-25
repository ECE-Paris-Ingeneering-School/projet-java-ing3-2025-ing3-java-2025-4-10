package dao;

import modele.RendezVous;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {

    // Ajouter un nouveau rendez-vous
    public boolean ajouterRendezVous(RendezVous r) {
        String requete = "INSERT INTO rdv (FK_ID_Patient, FK_ID_Spécialiste, FK_ID_Lieu, date, heure, motif) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, r.getIdPatient());
            stmt.setInt(2, r.getIdSpecialiste());
            stmt.setInt(3, r.getIdLieu());
            stmt.setString(4, r.getDate());
            stmt.setString(5, r.getHeure());
            stmt.setString(6, r.getMotif());

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
        String requete = "SELECT * FROM rdv WHERE FK_ID_Patient = ? ORDER BY date DESC, heure DESC";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idPatient);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RendezVous r = new RendezVous(
                        rs.getInt("ID_RDV"),
                        rs.getInt("FK_ID_Patient"),
                        rs.getInt("FK_ID_Spécialiste"),
                        rs.getInt("FK_ID_Lieu"),
                        rs.getString("Date"),
                        rs.getString("Heure"),
                        rs.getString("Motif")
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
        String requete = "SELECT * FROM rdv ORDER BY date DESC, heure DESC";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RendezVous r = new RendezVous(
                        rs.getInt("ID_RDV"),
                        rs.getInt("FK_ID_Patient"),
                        rs.getInt("FK_ID_Spécialiste"),
                        rs.getInt("FK_ID_Lieu"),
                        rs.getString("Date"),
                        rs.getString("Heure"),
                        rs.getString("Motif")
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
        String requete = "DELETE FROM RendezVous WHERE ID_RDV = ?";

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
