package modele;

public class Lieu {
    private int idLieu;
    private String adresse;
    private String ville;
    private String codePostal;

    //Constructeur
    public Lieu(int idLieu, String adresse, String ville, String codePostal) {
        this.idLieu = idLieu;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    //Getters
    public int getIdLieu() {return idLieu;}
    public String getAdresse() {return adresse;}
    public String getVille() {return ville;}
    public String getCodePostal() {return codePostal;}

}
