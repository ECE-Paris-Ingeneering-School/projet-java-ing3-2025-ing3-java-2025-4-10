package controleur;

// importation des classes nécessaires
import dao.RendezVousDAO;
import modele.RendezVous;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;
/**
 * Classe de contrôle pour gérer les rendez-vous.
 * Permet d'ajouter, supprimer et récupérer des rendez-vous.
 */
public class RendezVousControleur { // classe controleur pour gérer les rdv

    private final RendezVousDAO dao = new RendezVousDAO(); // instance de RendezVousDAO

    // récupère tous les rendez-vous (admin)
    public List<RendezVous> getTousLesRendezVous() {
        return dao.recupererTousLesRendezVous();
    }

    /**
     * Récupère les rendez-vous d'un spécialiste pour une date donnée.
     *
     * @param rdv l'identifiant du spécialiste
     * @return la liste des rendez-vous pour le spécialiste et la date donnés
     */
    public boolean ajouterRendezVous(RendezVous rdv) {
        return dao.ajouterRendezVous(rdv);
    }

    /**
     * Récupère les rendez-vous d'un spécialiste pour une date donnée.
     *
     * @param id l'identifiant du spécialiste
     * @return la liste des rendez-vous pour le spécialiste et la date donnés
     */
    public boolean supprimerRendezVous(int id) {
        return dao.supprimerRendezVousParId(id);
    }
    /**
     * Récupère les rendez-vous d'un spécialiste pour une date donnée.
     *
     * @param idSpecialiste l'identifiant du spécialiste
     * @param date la date pour laquelle on veut récupérer les rendez-vous
     * @return la liste des rendez-vous pour le spécialiste et la date donnés
     */
    public List<String> getHeuresPrises(int idSpecialiste, LocalDate date) {
        // Cette méthode va faire une requête SQL pour récupérer toutes les heures déjà réservées
        return dao.getHeuresPrises(idSpecialiste, date);
    }

    /**
     * Récupère les rendez-vous d'un patient.
     *
     * @param idPatient l'identifiant du patient
     * @return la liste des rendez-vous pour le patient donné
     */
    public List<RendezVous> getRendezVousParPatient(int idPatient) {
        return dao.recupererRendezVousParPatient(idPatient);
    }
}
