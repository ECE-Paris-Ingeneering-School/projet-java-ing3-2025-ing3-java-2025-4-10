package dao;

import modele.Lieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LieuDAO {

    public List<Lieu> recupererTousLesLieux() {
        List<Lieu> lieux = new ArrayList<>();
        String requete = "SELECT * FROM Lieu";

        try (Connection conn = ConnexionBDD.getConnexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(requete)) {

            while (rs.next()) {
                Lieu l = new Lieu(
                        rs.getInt("id_lieu"),
                        rs.getString("adresse"),
                        rs.getString("ville"),
                        rs.getString("codepostal")
                );
                lieux.add(l);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des lieux.");
            e.printStackTrace();
        }

        return lieux;
    }

    public boolean ajouterLieu(Lieu lieu) {
        String requete = "INSERT INTO Lieu (adresse, ville, code_postal) VALUES (?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setString(1, lieu.getAdresse());
            stmt.setString(2, lieu.getVille());
            stmt.setString(3, lieu.getCodePostal());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du lieu.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerLieuParId(int id) {
        String requete = "DELETE FROM Lieu WHERE id = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du lieu.");
            e.printStackTrace();
            return false;
        }
    }
}
