package modele;

public class User { // classe user

    private int idUser; // id de l'utilisateur
    private String email; // email de l'utilisateur
    private String motDePasse; // mot de passe de l'utilisateur
    private String role; // role de l'utilisateur (patient ou admin)

    // constructeur
    public User(int idUser, String email, String motDePasse, String role) {
        this.idUser = idUser;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // === getters ===

    public int getIdUser() { // récupérer l'id utilisateur
        return idUser;
    }

    public String getEmail() { // récupérer l'email
        return email;
    }

    public String getMotDePasse() { // récupérer le mot de passe
        return motDePasse;
    }

    public String getRole() { // récupérer le role
        return role;
    }
}

