package vue;

import javax.swing.*;
import java.awt.*;

public class ConnexionVue extends JFrame {

    private JTextField champEmail;
    private JPasswordField champMotDePasse;
    private JButton boutonConnexion;
    private JButton boutonInscription;

    public ConnexionVue() {
        setTitle("Connexion utilisateur");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Centre la fenêtre

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel labelEmail = new JLabel("Email :");
        champEmail = new JTextField();

        JLabel labelMotDePasse = new JLabel("Mot de passe :");
        champMotDePasse = new JPasswordField();

        boutonConnexion = new JButton("Se connecter");
        boutonInscription = new JButton("Créer un compte");

        panel.add(labelEmail);         panel.add(champEmail);
        panel.add(labelMotDePasse);    panel.add(champMotDePasse);
        panel.add(boutonConnexion);    panel.add(boutonInscription);

        add(panel);

        // Action : Se connecter
        boutonConnexion.addActionListener(e -> {
            String email = champEmail.getText();
            String motDePasse = new String(champMotDePasse.getPassword());

            JOptionPane.showMessageDialog(this,
                    "Connexion de : " + email + "\n(Mot de passe : " + motDePasse + ")");
        });

        // Action : Créer un compte
        boutonInscription.addActionListener(e -> {
            dispose(); // Ferme la fenêtre actuelle
            new InscriptionVue().setVisible(true); // Ouvre la vue d'inscription
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConnexionVue().setVisible(true));
    }
}
