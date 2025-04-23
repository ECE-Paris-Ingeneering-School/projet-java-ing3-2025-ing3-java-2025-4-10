package modele;

public class Specialiste {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String specialite;
    private String qualification;

    public Specialiste(int id, String nom, String prenom, String email, String specialite, String qualification) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.specialite = specialite;
        this.qualification = qualification;
    }

    // === GETTERS ===

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getQualification() {
        return qualification;
    }

    // === SETTERS ===

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    // === Affichage (utile pour ComboBox) ===
    @Override
    public String toString() {
        return specialite + " - Dr " + prenom + " " + nom;
    }
}
