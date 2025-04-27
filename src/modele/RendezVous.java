package modele;

public class RendezVous { // classe rendez-vous

    private int id; // id du rendez-vous
    private int idPatient; // id du patient
    private int idSpecialiste; // id du spécialiste
    private int idLieu; // id du lieu
    private String date; // date du rdv, format "YYYY-MM-DD"
    private String heure; // heure du rdv, format "HH:MM"
    private String motif; // motif du rendez-vous
    private int disponibilite; // etat de disponibilite du rdv

    // constructeur vide
    public RendezVous() {}

    // constructeur plein
    public RendezVous(int id, int idPatient, int idSpecialiste, int idLieu, String date, String heure, String motif) {
        this.id = id;
        this.idPatient = idPatient;
        this.idSpecialiste = idSpecialiste;
        this.idLieu = idLieu;
        this.date = date;
        this.heure = heure;
        this.motif = motif;
    }

    // === getters ===

    public int getId() { // récupérer l'id
        return id;
    }

    public int getIdPatient() { // récupérer l'id du patient
        return idPatient;
    }

    public int getIdSpecialiste() { // récupérer l'id du spécialiste
        return idSpecialiste;
    }

    public int getIdLieu() { // récupérer l'id du lieu
        return idLieu;
    }

    public String getDate() { // récupérer la date
        return date;
    }

    public String getHeure() { // récupérer l'heure
        return heure;
    }

    public String getMotif() { // récupérer le motif
        return motif;
    }

    public int getDisponibilite() { return disponibilite; }
    // === setters ===

    public void setDate(String date) { // modifier la date
        this.date = date;
    }

    public void setHeure(String heure) { // modifier l'heure
        this.heure = heure;
    }

    public void setMotif(String motif) { // modifier le motif
        this.motif = motif;
    }

    public void setId(int id) { // modifier l'id
        this.id = id;
    }

    public void setIdPatient(int idPatient) { // modifier l'id du patient
        this.idPatient = idPatient;
    }

    public void setIdSpecialiste(int idSpecialiste) { // modifier l'id du spécialiste
        this.idSpecialiste = idSpecialiste;
    }

    public void setIdLieu(int idLieu) { // modifier l'id du lieu
        this.idLieu = idLieu;
    }

    public void setDisponibilite(int disponibilite) { //modifie l'etat de disponibilité
        this.disponibilite = disponibilite;
    }

    // affichage lisible pour debug
    @Override
    public String toString() {
        return date + " à " + heure + " (Spécialiste #" + idSpecialiste + ", Lieu #" + idLieu + ")";
    }
}
