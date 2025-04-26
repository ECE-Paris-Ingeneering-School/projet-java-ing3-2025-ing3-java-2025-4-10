package modele;

public class Specialiste {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String specialite;


    
    public Specialiste(int id, String nom, String prenom, String email, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.specialite = specialite;
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


    // === Affichage (utile pour ComboBox) ===
    @Override
    public String toString() {
        return specialite + " - Dr " + prenom + " " + nom;
    }
}
