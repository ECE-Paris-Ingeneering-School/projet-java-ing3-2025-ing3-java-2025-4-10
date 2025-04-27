package modele;

/**
 * Constructeur de Lieu.
 *
 * @param id identifiant du lieu
 * @param adresse adresse complète du lieu
 * @param ville ville du lieu
 * @param codePostal code postal du lieu
 */

public class Lieu { // classe lieu

    private int id; // identifiant unique
    private String adresse; // adresse du lieu
    private String ville; // ville du lieu
    private String codePostal; // code postal du lieu

    // constructeur de lieu
    public Lieu(int id, String adresse, String ville, String codePostal) {
        this.id = id;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    // === getters ===

    public int getId() { // récupérer l'id
        return id;
    }

    public String getAdresse() { // récupérer l'adresse
        return adresse;
    }

    public String getVille() { // récupérer la ville
        return ville;
    }

    public String getCodePostal() { // récupérer le code postal
        return codePostal;
    }

    // === setters (si besoin) ===

    public void setAdresse(String adresse) { // modifier l'adresse
        this.adresse = adresse;
    }

    public void setVille(String ville) { // modifier la ville
        this.ville = ville;
    }

    public void setCodePostal(String codePostal) { // modifier le code postal
        this.codePostal = codePostal;
    }

    public void setId(int id) { // modifier l'id
        this.id = id;
    }

    // === affichage dans les interfaces (combo box etc) ===
    @Override
    public String toString() { // comment afficher l'objet
        return ville + " - " + adresse + " (" + codePostal + ")";
    }
}
