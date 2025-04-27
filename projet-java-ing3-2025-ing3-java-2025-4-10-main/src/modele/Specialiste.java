package modele;

public class Specialiste { // classe specialiste

    private int id; // id du specialiste
    private String nom; // nom du specialiste
    private String prenom; // prenom du specialiste
    private String email; // email du specialiste
    private String specialite; // specialite du specialiste

    // constructeur plein
    public Specialiste(int id, String nom, String prenom, String email, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.specialite = specialite;
    }

    // === getters ===

    public int getId() { // récupérer l'id
        return id;
    }

    public String getNom() { // récupérer le nom
        return nom;
    }

    public String getPrenom() { // récupérer le prenom
        return prenom;
    }

    public String getEmail() { // récupérer l'email
        return email;
    }

    public String getSpecialite() { // récupérer la specialite
        return specialite;
    }

    // === setters ===

    public void setNom(String nom) { // modifier le nom
        this.nom = nom;
    }

    public void setPrenom(String prenom) { // modifier le prenom
        this.prenom = prenom;
    }

    public void setEmail(String email) { // modifier l'email
        this.email = email;
    }

    public void setSpecialite(String specialite) { // modifier la specialite
        this.specialite = specialite;
    }

    // affichage utile pour les combobox
    @Override
    public String toString() {
        return specialite + " - Dr " + prenom + " " + nom;
    }
}
