package modele;

public class Note {

    private int idNote;
    private int idRDV;
    private int valeur; // entre 1 et 5

    public Note(int idNote, int idRDV, int valeur) {
        this.idNote = idNote;
        this.idRDV = idRDV;
        this.valeur = valeur;
    }

    public int getIdNote() {
        return idNote;
    }

    public int getIdRDV() {
        return idRDV;
    }

    public int getValeur() {
        return valeur;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public void setIdRDV(int idRDV) {
        this.idRDV = idRDV;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}