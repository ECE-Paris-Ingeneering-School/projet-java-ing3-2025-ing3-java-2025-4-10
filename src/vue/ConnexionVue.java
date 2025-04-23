package vue;

import dao.UserDAO;
import dao.UserDAO.Utilisateur;

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
        setLocationRelativeTo(null);

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

        boutonConnexion.addActionListener(e -> {
            String email = champEmail.getText().trim();
            String mdp = new String(champMotDePasse.getPassword()).trim();

            if (email.isEmpty() || mdp.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            UserDAO dao = new UserDAO();
            Utilisateur utilisateur = dao.verifierConnexion(email, mdp);

            if (utilisateur != null) {
                dispose();
                new MenuPrincipalVue(utilisateur.role, utilisateur.id).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Identifiants incorrects.");
            }
        });

        boutonInscription.addActionListener(e -> {
            dispose();
            new InscriptionVue().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConnexionVue().setVisible(true));
    }
}
