package dao;

// importation des classes nécessaires
import modele.Patient;
import java.sql.*;
/**
 * Classe PatientDAO pour gérer les opérations liées aux patients dans la base de données.
 * Elle permet de vérifier l'existence d'un email, d'enregistrer un patient, de récupérer ses informations
 * et de modifier ses informations.
 */
public class PatientDAO { // classe pour accéder aux données des patients

    // méthode pour vérifier si un email existe déjà dans la table user
    public boolean emailExisteDeja(String email) {
        String requete = "SELECT ID_User FROM  `user` WHERE email = ?"; // requête pour chercher email

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setString(1, email); // remplacement du ?
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true si résultat trouvé

        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'email.");
            e.printStackTrace();
            return true; // en cas d'erreur, on suppose que l'email existe
        }
    }

    /**
     * Méthode pour enregistrer un patient dans la base de données.
     * Elle insère d'abord l'utilisateur dans la table `user`, puis le patient dans la table `patient`.
     * @param nom Le nom du patient.
     * @param prenom Le prénom du patient.
     * @param email L'email du patient.
     * @param motDePasse Le mot de passe de l'utilisateur.
     * @param ancien Indique si le patient est ancien ou non (0 ou 1).
     * @return true si l'enregistrement a réussi, false sinon.
     */
    public boolean enregistrerPatient(String nom, String prenom, String email, String motDePasse, String ancien) {
        String requeteUser = "INSERT INTO `user` (email, MotDePasse, Role) VALUES (?, ?, 'patient')"; // insertion user
        String requetePatient = "INSERT INTO `patient` (FK_ID_User, Nom, Prenom, Email, Ancien) VALUES (?, ?, ?, ?, ?)"; // insertion patient

        try (Connection conn = ConnexionBDD.getConnexion()) {
            conn.setAutoCommit(false); // on commence une transaction

            int idUser; // id généré pour l'user

            try (PreparedStatement stmtUser = conn.prepareStatement(requeteUser, Statement.RETURN_GENERATED_KEYS)) {
                stmtUser.setString(1, email);
                stmtUser.setString(2, motDePasse);
                stmtUser.executeUpdate();

                ResultSet keys = stmtUser.getGeneratedKeys();
                if (keys.next()) {
                    idUser = keys.getInt(1); // on récupère l'id généré
                } else {
                    conn.rollback(); // problème, on annule
                    return false;
                }
            }

            try (PreparedStatement stmtPatient = conn.prepareStatement(requetePatient)) {
                stmtPatient.setInt(1, idUser);
                stmtPatient.setString(2, nom);
                stmtPatient.setString(3, prenom);
                stmtPatient.setString(4, email);
                stmtPatient.setString(5, ancien);
                stmtPatient.executeUpdate();
            }

            conn.commit(); // on valide tout
            return true;

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'enregistrement du patient.");
            e.printStackTrace();
            return false;
        }
    }

    // méthode pour récupérer les infos d'un patient par idUser
    public Patient recupererInfosPatient(int idUser) {
        String requete = "SELECT nom, prenom FROM Patient WHERE idUser = ?"; // requête récupération

        try (Connection conn = ConnexionBDD.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Patient(idUser, rs.getString("nom"), rs.getString("prenom")); // création objet patient
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des infos patient.");
            e.printStackTrace();
        }

        return null; // si pas trouvé
    }

    /**
     * Méthode pour modifier les informations d'un patient et son mot de passe.
     * Elle met à jour la table `patient` et la table `user`.
     * @param idUser L'ID de l'utilisateur (patient).
     * @param nom Le nouveau nom du patient.
     * @param prenom Le nouveau prénom du patient.
     * @param motDePasse Le nouveau mot de passe de l'utilisateur.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean modifierInfosPatient(int idUser, String nom, String prenom, String motDePasse) {
        String requete1 = "UPDATE Patient SET nom = ?, prenom = ? WHERE idUser = ?"; // update patient
        String requete2 = "UPDATE User SET mot_de_passe = ? WHERE id = ?"; // update mot de passe user

        try (Connection conn = ConnexionBDD.getConnexion()) {
            conn.setAutoCommit(false); // transaction

            try (PreparedStatement stmt1 = conn.prepareStatement(requete1);
                 PreparedStatement stmt2 = conn.prepareStatement(requete2)) {

                // modification patient
                stmt1.setString(1, nom);
                stmt1.setString(2, prenom);
                stmt1.setInt(3, idUser);
                stmt1.executeUpdate();

                // modification user
                stmt2.setString(1, motDePasse);
                stmt2.setInt(2, idUser);
                stmt2.executeUpdate();

                conn.commit(); // valider
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du patient.");
            e.printStackTrace();
            return false;
        }
    }
}
