package controleur;

import dao.SpecialisteDAO;
import modele.Specialiste;

import java.util.List;

public class SpecialisteControleur {

    private final SpecialisteDAO dao = new SpecialisteDAO();

    public List<Specialiste> getTousLesSpecialistes() {
        return dao.recupererTousLesSpecialistes();
    }

    public boolean ajouterSpecialiste(Specialiste s) {
        return dao.ajouterSpecialiste(s);
    }

    public boolean supprimerSpecialisteParId(int id) {
        return dao.supprimerSpecialisteParId(id);
    }
}
