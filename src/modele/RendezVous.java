package modele;

public class RendezVous {

    private int id;
    private int idPatient;
    private int idSpecialiste;
    private int idLieu;
    private String date;      // Format : "YYYY-MM-DD"
    private String heure;     // Format : "HH:MM"
    private String note;

    public RendezVous(int id, int idPatient, int idSpecialiste, int idLieu, String date, String heure, String note) {
        this.id = id;
        this.idPatient = idPatient;
        this.idSpecialiste = idSpecialiste;
        this.idLieu = idLieu;
        this.date = date;
        this.heure = heure;
        this.note = note;
    }

    // === GETTERS ===

    public int getId() {
        return id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public int getIdSpecialiste() {
        return idSpecialiste;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getNote() {
        return note;
    }

    // === SETTERS ===

    public void setDate(String date) {
        this.date = date;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public void setIdSpecialiste(int idSpecialiste) {
        this.idSpecialiste = idSpecialiste;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    @Override
    public String toString() {
        return date + " à " + heure + " (Spécialiste #" + idSpecialiste + ", Lieu #" + idLieu + ")";
    }
}
