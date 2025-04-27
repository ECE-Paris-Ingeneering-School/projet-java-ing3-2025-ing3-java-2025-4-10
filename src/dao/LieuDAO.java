package dao;

/**
DAO pour gérer les opérations liées aux patients.
Permet d'inscrire un patient, récupérer ses informations et modifier son profil.
 */


// importation des classes nécessaires
import modele.Lieu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LieuDAO { // classe d'accès aux données pour les lieux
    /**
     * Récupère tous les lieux de la base de données.
     *
     * @return une liste de tous les lieux
     */
    public List<Lieu> recupererTousLesLieux() { // méthode pour récupérer tous les lieux
        List<Lieu> lieux = new ArrayList<>(); // liste des lieux à remplir
        String requete = "SELECT * FROM Lieu"; // requête SQL pour tout prendre

        try (Connection conn = ConnexionBDD.getConnexion(); // ouvrir connexion
             Statement stmt = conn.createStatement(); // préparer l'exécution
             ResultSet rs = stmt.executeQuery(requete)) { // exécuter et stocker résultat

            // tant qu'on a des résultats
            while (rs.next()) {
                Lieu l = new Lieu(
                        rs.getInt("id_lieu"),
                        rs.getString("adresse"),
                        rs.getString("ville"),
                        rs.getString("codepostal")
                );
                lieux.add(l); // on ajoute chaque lieu récupéré à la liste
            }

        } catch (SQLException e) { // en cas d'erreur
            System.err.println("Erreur lors de la récupération des lieux.");
            e.printStackTrace();
        }

        return lieux; // retourner la liste finale
    }
    /**
     * Récupère un lieu par son identifiant.
     *
     * @param id l'identifiant du lieu
     * @return le lieu correspondant à l'identifiant
     */
    public boolean ajouterLieu(Lieu lieu) { // méthode pour insérer un lieu
        String requete = "INSERT INTO Lieu (Adresse, Ville, CodePostal) VALUES (?, ?, ?)"; // requête insertion

        try (Connection conn = ConnexionBDD.getConnexion(); // connexion
             PreparedStatement stmt = conn.prepareStatement(requete)) { // requête préparée

            // remplacer les ? par les vraies valeurs
            stmt.setString(1, lieu.getAdresse());
            stmt.setString(2, lieu.getVille());
            stmt.setString(3, lieu.getCodePostal());

            return stmt.executeUpdate() > 0; // retourner vrai si succès

        } catch (SQLException e) { // en cas d'erreur
            System.err.println("Erreur lors de l'ajout du lieu.");
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Supprime un lieu par son identifiant.
     *
     * @param id l'identifiant du lieu à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean supprimerLieuParId(int id) { // méthode pour supprimer un lieu
        String requete = "DELETE FROM Lieu WHERE id = ?"; // requête suppression

        try (Connection conn = ConnexionBDD.getConnexion(); // connexion
             PreparedStatement stmt = conn.prepareStatement(requete)) { // requête préparée

            stmt.setInt(1, id); // remplacer le ? par l'id
            return stmt.executeUpdate() > 0; // succès si au moins une ligne supprimée

        } catch (SQLException e) { // en cas d'erreur
            System.err.println("Erreur lors de la suppression du lieu.");
            e.printStackTrace();
            return false;
        }
    }
}
