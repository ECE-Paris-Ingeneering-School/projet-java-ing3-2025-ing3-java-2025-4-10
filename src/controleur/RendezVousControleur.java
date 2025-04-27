package controleur;

// importation des classes nécessaires
import dao.RendezVousDAO;
import modele.RendezVous;
import java.util.List;

public class RendezVousControleur { // classe controleur pour gérer les rdv

    private final RendezVousDAO dao = new RendezVousDAO(); // instance de RendezVousDAO

    // récupère tous les rendez-vous (admin)
    public List<RendezVous> getTousLesRendezVous() {
        return dao.recupererTousLesRendezVous();
    }

    // ajoute un nouveau rendez-vous
    public boolean ajouterRendezVous(RendezVous rdv) {
        return dao.ajouterRendezVous(rdv);
    }

    // supprime un rendez-vous par son id
    public boolean supprimerRendezVous(int id) {
        return dao.supprimerRendezVousParId(id);
    }

    // récupère les rendez-vous d'un patient spécifique
    public List<RendezVous> getRendezVousParPatient(int idPatient) {
        return dao.recupererRendezVousParPatient(idPatient);
    }
}
