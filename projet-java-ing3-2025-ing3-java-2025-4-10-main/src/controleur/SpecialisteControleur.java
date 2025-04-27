package controleur;

// importation des classes nécessaires
import dao.SpecialisteDAO;
import modele.Specialiste;
import java.util.List;

public class SpecialisteControleur { // classe controleur pour gérer les spécialistes

    private final SpecialisteDAO dao = new SpecialisteDAO(); // instance de SpecialisteDAO

    // récupère tous les spécialistes
    public List<Specialiste> getTousLesSpecialistes() {
        return dao.recupererTousLesSpecialistes();
    }

    // ajoute un spécialiste
    public boolean ajouterSpecialiste(Specialiste s) {
        return dao.ajouterSpecialiste(s);
    }

    // supprime un spécialiste par son id
    public boolean supprimerSpecialisteParId(int id) {
        return dao.supprimerSpecialisteParId(id);
    }
}
