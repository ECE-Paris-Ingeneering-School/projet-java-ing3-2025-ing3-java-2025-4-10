package modele;

public class Lieu {

    private int id;
    private String adresse;
    private String ville;
    private String codePostal;

    public Lieu(int id, String adresse, String ville, String codePostal) {
        this.id = id;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    // === GETTERS ===

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    // === SETTERS (si besoin) ===

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setId(int id) {
        this.id = id;
    }

    // === Affichage dans les interfaces ===

    @Override
    public String toString() {
        return ville + " - " + adresse + " (" + codePostal + ")";
    }
}
