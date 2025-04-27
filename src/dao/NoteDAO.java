package dao;

import modele.Note;
import java.sql.*;

public class NoteDAO {

    public boolean ajouterNote(Note note) {
        String requete = "INSERT INTO Note (FK_ID_RDV, Valeur) VALUES (?, ?)";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, note.getIdRDV());
            stmt.setInt(2, note.getValeur());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la note.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean noteExisteDeja(int idRDV) {
        // Correction ici : il manquait les colonnes après SELECT
        String requete = "SELECT 1 FROM Note WHERE FK_ID_RDV = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idRDV);
            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'existence de la note.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifierNote(Note note) {
        String requete = "UPDATE Note SET Valeur = ? WHERE FK_ID_RDV = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, note.getValeur());
            stmt.setInt(2, note.getIdRDV());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la note.");
            e.printStackTrace();
            return false;
        }
    }
}
