package modele;

public class Patient {

    private int idUser;        // Identifiant utilisateur (lié à la table User)
    private String nom;
    private String prenom;

    public Patient(int idUser, String nom, String prenom) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
    }

    // === GETTERS ===

    public int getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    // === SETTERS ===

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    // === Pour affichage ou debug éventuel ===
    @Override
    public String toString() {
        return prenom + " " + nom + " (ID: " + idUser + ")";
    }
}
