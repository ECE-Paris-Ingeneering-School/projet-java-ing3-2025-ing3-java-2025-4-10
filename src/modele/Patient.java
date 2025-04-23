package modele;

public class Patient {
    private int idPatient;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private boolean ancien;
    private int idUser;

    //Constructeur
    public Patient(int idPatient, String nom, String prenom, String email, String motDePasse, boolean ancien, int idUser) {
        this.idPatient = idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.ancien = ancien;
        this.idUser = idUser;
    }

    //getters
    public int getIdPatient() {return idPatient;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getEmail() {return email;}
    public String getMotDePasse() {return motDePasse;}
    public boolean isAncien() {return ancien;}
    public int getIdUser() {return idUser;}
}
