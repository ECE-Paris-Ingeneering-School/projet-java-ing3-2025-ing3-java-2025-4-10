package controleur;

import dao.LieuDAO;
import modele.Lieu;

import java.util.List;

public class LieuControleur {

    private final LieuDAO dao = new LieuDAO();

    public List<Lieu> getTousLesLieux() {
        return dao.recupererTousLesLieux();
    }

    public boolean ajouterLieu(Lieu lieu) {
        return dao.ajouterLieu(lieu);
    }

    public boolean supprimerLieuParId(int id) {
        return dao.supprimerLieuParId(id);
    }
}
