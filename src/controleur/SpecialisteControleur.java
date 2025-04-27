package controleur;

// importation des classes nécessaires
import dao.SpecialisteDAO;
import modele.Specialiste;
import java.util.List;
/**
 * Classe de contrôle pour gérer les spécialistes.
 * Permet d'ajouter, supprimer et récupérer des spécialistes.
 */
public class SpecialisteControleur { // classe controleur pour gérer les spécialistes

    private final SpecialisteDAO dao = new SpecialisteDAO(); // instance de SpecialisteDAO

    /**
     * Récupère tous les spécialistes.
     *
     * @return la liste de tous les spécialistes
     */
    public List<Specialiste> getTousLesSpecialistes() {
        return dao.recupererTousLesSpecialistes();
    }

    /**
     * Ajoute un spécialiste.
     *
     * @param s le spécialiste à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean ajouterSpecialiste(Specialiste s) {
        return dao.ajouterSpecialiste(s);
    }

    /**
     * Supprime un spécialiste par son identifiant.
     *
     * @param id l'identifiant du spécialiste à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean supprimerSpecialisteParId(int id) {
        return dao.supprimerSpecialisteParId(id);
    }
}
