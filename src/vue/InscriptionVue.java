package vue;

import javax.swing.*;
import java.awt.*;

public class InscriptionVue extends JFrame {

    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champEmail;
    private JPasswordField champMotDePasse;
    private JButton boutonInscription;
    private JButton boutonRetour;

    public InscriptionVue() {
        setTitle("Création d'un compte patient");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centre la fenêtre

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel labelNom = new JLabel("Nom :");
        champNom = new JTextField();

        JLabel labelPrenom = new JLabel("Prénom :");
        champPrenom = new JTextField();

        JLabel labelEmail = new JLabel("Email :");
        champEmail = new JTextField();

        JLabel labelMotDePasse = new JLabel("Mot de passe :");
        champMotDePasse = new JPasswordField();

        boutonInscription = new JButton("S'inscrire");
        boutonRetour = new JButton("Retour");

        // Ajout des composants au panel
        panel.add(labelNom);          panel.add(champNom);
        panel.add(labelPrenom);       panel.add(champPrenom);
        panel.add(labelEmail);        panel.add(champEmail);
        panel.add(labelMotDePasse);   panel.add(champMotDePasse);
        panel.add(boutonInscription); panel.add(boutonRetour);

        add(panel);

        // Action du bouton "S'inscrire"
        boutonInscription.addActionListener(e -> {
            String nom = champNom.getText();
            String prenom = champPrenom.getText();
            String email = champEmail.getText();
            String motDePasse = new String(champMotDePasse.getPassword());

            // Ici tu appelleras le contrôleur plus tard
            JOptionPane.showMessageDialog(this,
                    "Compte créé pour : " + prenom + " " + nom + "\nEmail : " + email);
        });

        // Action du bouton "Retour"
        boutonRetour.addActionListener(e -> {
            dispose(); // Ferme la fenêtre actuelle
            new ConnexionVue().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InscriptionVue().setVisible(true));
    }
}
