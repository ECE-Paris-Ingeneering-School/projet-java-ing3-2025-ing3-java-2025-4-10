package modele;

public class Note {
    private int idNote;
    private String commentaire;
    private int idRDV;

    public Note(int idNote, String commentaire, int idRDV) {
        this.idNote = idNote;
        this.commentaire = commentaire;
        this.idRDV = idRDV;
    }

    //getters
    public int getIdNote() { return idNote;}
    public String getCommentaire() { return commentaire;}
    public int getIdRDV() { return idRDV;}
}
