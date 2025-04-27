package controleur;

// importation des classes nécessaires
import dao.LieuDAO;
import modele.Lieu;
import java.util.List;
/**
 * Classe controleur pour gérer les lieux.
 * Permet d'ajouter, supprimer et récupérer des lieux.
 */
public class LieuControleur { // classe controleur pour gérer les lieux

    private final LieuDAO dao = new LieuDAO(); // création d'une instance de LieuDAO

    /**
     * Récupère tous les lieux.
     * @return une liste de tous les lieux
     */
    public List<Lieu> getTousLesLieux() {
        return dao.recupererTousLesLieux();
    }

    /**
     * Ajoute un nouveau lieu.
     * @param lieu le lieu à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean ajouterLieu(Lieu lieu) {
        return dao.ajouterLieu(lieu);
    }

    /**
     * Supprime un lieu par son id.
     * @param id l'id du lieu à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean supprimerLieuParId(int id) {
        return dao.supprimerLieuParId(id);
    }
}
