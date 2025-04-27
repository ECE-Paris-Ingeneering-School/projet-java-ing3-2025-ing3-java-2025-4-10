package controleur;

import dao.NoteDAO;
import modele.Note;

public class NoteControleur {

    private final NoteDAO dao = new NoteDAO();

    public boolean ajouterNote(Note note) {
        return dao.ajouterNote(note);
    }

    public boolean noteExisteDeja(int idRDV) {
        return dao.noteExisteDeja(idRDV);
    }

    public boolean modifierNote(Note note) {
        return dao.modifierNote(note);
    }
}
