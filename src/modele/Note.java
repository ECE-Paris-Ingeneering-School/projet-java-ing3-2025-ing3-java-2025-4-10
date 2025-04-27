package modele; // package modele

public class Note { // classe note

    private int idNote; // id unique de la note
    private String commentaire; // texte du commentaire
    private int idRDV; // id du rendez-vous lié

    // constructeur
    public Note(int idNote, String commentaire, int idRDV) {
        this.idNote = idNote;
        this.commentaire = commentaire;
        this.idRDV = idRDV;
    }

    // === getters ===

    public int getIdNote() { // récupérer l'id de la note
        return idNote;
    }

    public String getCommentaire() { // récupérer le commentaire
        return commentaire;
    }

    public int getIdRDV() { // récupérer l'id du rendez-vous associé
        return idRDV;
    }
}

