package controleur;

import dao.NoteDAO;
import modele.Note;
/**
 * Classe de contrôle pour gérer les notes.
 */
public class NoteControleur {

    private final NoteDAO dao = new NoteDAO();
    /**
     * Ajoute une nouvelle note.
     *
     * @param note la note à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    public boolean ajouterNote(Note note) {
        return dao.ajouterNote(note);
    }
    /**
     * Vérifie si une note existe déjà pour un rendez-vous donné.
     *
     * @param idRDV l'identifiant du rendez-vous
     * @return true si la note existe déjà, false sinon
     */
    public boolean noteExisteDeja(int idRDV) {
        return dao.noteExisteDeja(idRDV);
    }
    /**
     * Modifie une note existante.
     *
     * @param note la note à modifier
     * @return true si la modification a réussi, false sinon
     */
    public boolean modifierNote(Note note) {
        return dao.modifierNote(note);
    }
}
