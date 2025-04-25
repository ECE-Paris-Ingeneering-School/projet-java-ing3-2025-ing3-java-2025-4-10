package dao;

import dao.ConnexionBDD;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.SQLException;

import static dao.ConnexionBDD.fermerConnexion;
import static dao.ConnexionBDD.getConnexion;

public class DBConnectionTest {
    public static void main(String[] args) {
        try {
            // Établir la connexion
            Connection conn = getConnexion();

            // Tu peux ajouter des requêtes ici si tu veux tester quelque chose

            // Fermer la connexion proprement
            fermerConnexion();
        } catch (SQLException e) {
            System.err.println("Une erreur est survenue : " + e.getMessage());
        }
    }
}
