import vue.ConnexionVue;

import javax.swing.*;

/**
 * Classe principale de l'application.
 * Lance l'application en affichant la fenêtre de connexion.
 */
public class Main {
    public static void main(String[] args) {
        // on s'assure que tout est lancé correctement dans l'EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            ConnexionVue connexion = new ConnexionVue(); // création de la fenêtre de connexion
            connexion.setVisible(true); // on l'affiche
        });
    }
}