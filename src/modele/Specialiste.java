package modele;

public class Specialiste{
    private int idSpecialiste;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String specialite;
    private String qualification;
    private int idUser;

    //Constructeur
    public Specialiste(int idSpecialiste, String nom, String prenom, String email, String motDePasse, String specialite, String qualification,int idUser) {
        this.idSpecialiste = idSpecialiste;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.specialite = specialite;
        this.qualification = qualification;
        this.idUser = idUser;
    }

    //getters
    public int getIdSpecialiste() {return idSpecialiste;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getEmail() {return email;}
    public String getMotDePasse() {return motDePasse;}
    public String getSpecialite() {return specialite;}
    public String getQualification() {return qualification;}
    public int getIdUser() {return idUser;}


}
