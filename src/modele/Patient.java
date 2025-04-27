package modele; // package modele

public class Patient { // classe patient

    private int idUser; // identifiant utilisateur (lié à la table User)
    private String nom; // nom du patient
    private String prenom; // prenom du patient

    // constructeur
    public Patient(int idUser, String nom, String prenom) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
    }

    // === getters ===

    public int getIdUser() { // récupérer l'id user
        return idUser;
    }

    public String getNom() { // récupérer le nom
        return nom;
    }

    public String getPrenom() { // récupérer le prénom
        return prenom;
    }

    // === setters ===

    public void setNom(String nom) { // changer le nom
        this.nom = nom;
    }

    public void setPrenom(String prenom) { // changer le prénom
        this.prenom = prenom;
    }

    public void setIdUser(int idUser) { // changer l'id user
        this.idUser = idUser;
    }

    // affichage lisible pour debug ou affichage
    @Override
    public String toString() {
        return prenom + " " + nom + " (ID: " + idUser + ")";
    }
}
