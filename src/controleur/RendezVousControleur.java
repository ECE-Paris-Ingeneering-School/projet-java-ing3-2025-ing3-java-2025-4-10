package controleur;

// importation des classes nécessaires
import dao.RendezVousDAO;
import modele.RendezVous;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

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

    public List<String> getHeuresPrises(int idSpecialiste, LocalDate date) {
        // Cette méthode va faire une requête SQL pour récupérer toutes les heures déjà réservées
        return dao.getHeuresPrises(idSpecialiste, date);
    }

    // récupère les rendez-vous d'un patient spécifique
    public List<RendezVous> getRendezVousParPatient(int idPatient) {
        return dao.recupererRendezVousParPatient(idPatient);
    }
}
