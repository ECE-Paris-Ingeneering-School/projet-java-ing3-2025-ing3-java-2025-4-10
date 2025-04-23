package dao;

import modele.Patient;

import java.sql.*;

public class PatientDAO {

    // Vérifie si l'email existe déjà dans la table User
    public boolean emailExisteDeja(String email) {
        String requete = "SELECT id FROM User WHERE email = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'email.");
            e.printStackTrace();
            return true; // En cas d'erreur, on suppose que c'est déjà pris
        }
    }

    // Inscrit un patient (User + Patient)
    public boolean enregistrerPatient(String nom, String prenom, String email, String motDePasse) {
        String requeteUser = "INSERT INTO User (email, mot_de_passe, role) VALUES (?, ?, 'patient')";
        String requetePatient = "INSERT INTO Patient (idUser, nom, prenom) VALUES (?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnexion()) {
            conn.setAutoCommit(false); // Démarrer transaction

            int idUser;

            try (PreparedStatement stmtUser = conn.prepareStatement(requeteUser, Statement.RETURN_GENERATED_KEYS)) {
                stmtUser.setString(1, email);
                stmtUser.setString(2, motDePasse);
                stmtUser.executeUpdate();

                ResultSet keys = stmtUser.getGeneratedKeys();
                if (keys.next()) {
                    idUser = keys.getInt(1);
                } else {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement stmtPatient = conn.prepareStatement(requetePatient)) {
                stmtPatient.setInt(1, idUser);
                stmtPatient.setString(2, nom);
                stmtPatient.setString(3, prenom);
                stmtPatient.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'enregistrement du patient.");
            e.printStackTrace();
            return false;
        }
    }

    // Récupère les infos du patient à partir de l'idUser
    public Patient recupererInfosPatient(int idUser) {
        String requete = "SELECT nom, prenom FROM Patient WHERE idUser = ?";

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Patient(idUser, rs.getString("nom"), rs.getString("prenom"));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des infos patient.");
            e.printStackTrace();
        }

        return null;
    }

    // Met à jour nom, prénom, mot de passe
    public boolean modifierInfosPatient(int idUser, String nom, String prenom, String motDePasse) {
        String requete1 = "UPDATE Patient SET nom = ?, prenom = ? WHERE idUser = ?";
        String requete2 = "UPDATE User SET mot_de_passe = ? WHERE id = ?";

        try (Connection conn = ConnexionBDD.getConnexion()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(requete1);
                 PreparedStatement stmt2 = conn.prepareStatement(requete2)) {

                stmt1.setString(1, nom);
                stmt1.setString(2, prenom);
                stmt1.setInt(3, idUser);
                stmt1.executeUpdate();

                stmt2.setString(1, motDePasse);
                stmt2.setInt(2, idUser);
                stmt2.executeUpdate();

                conn.commit();
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du patient.");
            e.printStackTrace();
            return false;
        }
    }
}
