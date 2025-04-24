package controleur;

import dao.RendezVousDAO;
import modele.RendezVous;

import java.util.List;

public class RendezVousControleur {

    private final RendezVousDAO dao = new RendezVousDAO();

    public List<RendezVous> getTousLesRendezVous() {
        return dao.recupererTousLesRendezVous();
    }

    public boolean ajouterRendezVous(RendezVous rdv) {
        return dao.ajouterRendezVous(rdv);
    }

    public boolean supprimerRendezVous(int id) {
        return dao.supprimerRendezVousParId(id);
    }

    public List<RendezVous> getRendezVousParPatient(int idPatient) {
        return dao.recupererRendezVousParPatient(idPatient);
    }
}
