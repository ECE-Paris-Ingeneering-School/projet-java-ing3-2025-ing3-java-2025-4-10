package dao;

// importation des classes nécessaires
import modele.RendezVous;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour gérer les opérations liées aux rendez-vous dans la base de données.
 * Contient des méthodes pour ajouter, récupérer et supprimer des rendez-vous.
 */
public class RendezVousDAO { // classe pour accéder aux données des rendez-vous

    // méthode pour ajouter un nouveau rendez-vous
    public boolean ajouterRendezVous(RendezVous r) {
        String requete = "INSERT INTO rdv (FK_ID_Patient, FK_ID_Spécialiste, FK_ID_Lieu, date, heure, motif, disponibilite) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, r.getIdPatient());
            stmt.setInt(2, r.getIdSpecialiste());
            stmt.setInt(3, r.getIdLieu());
            stmt.setString(4, r.getDate());
            stmt.setString(5, r.getHeure());
            stmt.setString(6, r.getMotif());
            stmt.setInt(7, 1);

            return stmt.executeUpdate() > 0; // succès si au moins une ligne ajoutée

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du rendez-vous.");
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Méthode pour récupérer toutes les heures déjà réservées pour un spécialiste à une date donnée.
     * @param idSpecialiste L'identifiant du spécialiste.
     * @param date La date pour laquelle on veut récupérer les heures réservées.
     * @return Une liste de chaînes de caractères représentant les heures réservées au format "HH:mm".
     */
    public List<String> getHeuresPrises(int idSpecialiste, LocalDate date) {
        List<String> heures = new ArrayList<>();
        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT heure FROM rdv WHERE FK_ID_Spécialiste = ? AND date = ? AND disponibilite = 1"
             )) {
            stmt.setInt(1, idSpecialiste);
            stmt.setString(2, date.toString());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String heureComplete = rs.getString("heure"); // format "HH:mm:ss"
                if (heureComplete != null && heureComplete.length() >= 5) {
                    String heureFormatee = heureComplete.substring(0, 5); // on ne garde que "HH:mm"
                    heures.add(heureFormatee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heures;
    }

    /**
     * Méthode pour récupérer tous les rendez-vous d'un patient donné.
     * @param idPatient L'identifiant du patient.
     * @return Une liste de rendez-vous associés au patient.
     */
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
                liste.add(r); // ajout du rendez-vous dans la liste
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des RDV.");
            e.printStackTrace();
        }

        return liste; // retourne la liste
    }

    /**
     * Méthode pour récupérer tous les rendez-vous de la base de données.
     * @return Une liste de tous les rendez-vous.
     */
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
                liste.add(r); // ajout du rendez-vous dans la liste
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des RDV (admin).");
            e.printStackTrace();
        }

        return liste; // retourne la liste
    }

    /**
     * Méthode pour supprimer un rendez-vous par son identifiant.
     * @param id L'identifiant du rendez-vous à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    public boolean supprimerRendezVousParId(int id) {
        String requete = "DELETE FROM rdv WHERE ID_RDV = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0; // true si une ligne supprimée

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du RDV.");
            e.printStackTrace();
            return false;
        }
    }
}
