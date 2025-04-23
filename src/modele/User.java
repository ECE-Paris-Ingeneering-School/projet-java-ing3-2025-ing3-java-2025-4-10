package modele;

public class User {
    private int idUser;
    private String email;
    private String motDePasse;
    private String role;

    //constructeur
    public User(int idUser, String email, String motDePasse, String role) {
        this.idUser = idUser;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    //getters
    public int getIdUser() {return idUser;}
    public String getEmail() {return email;}
    public String getMotDePasse() {return motDePasse;}
    public String getRole() {return role;}
}
