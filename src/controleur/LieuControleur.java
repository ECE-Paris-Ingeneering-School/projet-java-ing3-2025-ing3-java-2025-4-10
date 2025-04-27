package controleur;

// importation des classes nécessaires
import dao.LieuDAO;
import modele.Lieu;
import java.util.List;

public class LieuControleur { // classe controleur pour gérer les lieux

    private final LieuDAO dao = new LieuDAO(); // création d'une instance de LieuDAO

    // récupère tous les lieux depuis la base
    public List<Lieu> getTousLesLieux() {
        return dao.recupererTousLesLieux();
    }

    // ajoute un nouveau lieu
    public boolean ajouterLieu(Lieu lieu) {
        return dao.ajouterLieu(lieu);
    }

    // supprime un lieu par son id
    public boolean supprimerLieuParId(int id) {
        return dao.supprimerLieuParId(id);
    }
}
