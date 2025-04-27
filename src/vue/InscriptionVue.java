package vue;

// importation des classes nécessaires
import dao.PatientDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InscriptionVue extends JFrame { // classe inscription vue qui hérite de jframe

    private JTextField champNom; // champ texte pour le nom
    private JTextField champPrenom; // champ texte pour le prenom
    private JTextField champEmail; // champ texte pour l'email
    private JPasswordField champMotDePasse; // champ password pour le mot de passe
    private JTextField champAncien; // champ texte pour l'ancienneté
    private JButton boutonInscription; // bouton incription
    private JButton boutonRetour; // bouton retour

    public InscriptionVue() { // constructeur qui appelle l'initialisation de la page
        setTitle("Création d'un compte patient"); // titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fermer l'appli si on ferme la fenêtre
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ouvrir en plein écran
        setLocationRelativeTo(null); // centrer la fenêtre

        initialiserInterface(); // création de l'interface
    }

    private void initialiserInterface() { // méthode pour dessiner la page
        JPanel fond = new JPanel(new GridBagLayout()); // fond bleu ciel
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel(); // panneau blanc au centre
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)
        )); // définit les marges et les bordures
        panel.setMaximumSize(new Dimension(500, 500)); // taille max du bloc blanc

        JLabel titre = new JLabel("Créer un compte patient"); // titre principal
        titre.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT); // centré horizontalement
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        panel.add(createInputRow("Nom :", champNom = new JTextField())); // crée le champ nom
        panel.add(createInputRow("Prénom :", champPrenom = new JTextField())); // crée le champ prenom
        panel.add(createInputRow("Email :", champEmail = new JTextField())); // crée le champ email
        panel.add(createInputRow("Ancien (0 si new patient 1 sinon) :", champAncien = new JTextField())); // crée le champ ancien
        panel.add(createInputRow("Mot de passe :", champMotDePasse = new JPasswordField())); // crée le champ mot de passe

        panel.add(Box.createVerticalStrut(20));
        boutonInscription = createStyledButton("S'inscrire");
        panel.add(boutonInscription); //ajoute le bouton s'inscrire
        panel.add(Box.createVerticalStrut(10));
        boutonRetour = createStyledButton("Retour");
        panel.add(boutonRetour); // ajoute le bouton retour

        fond.add(panel); // on ajoute tout au fond
        setContentPane(fond); // on définit le fon

        boutonInscription.addActionListener(e -> inscrire()); // action s'incrire
        boutonRetour.addActionListener(e -> { // action retour
            dispose(); // fermer la fenêtre actuelle
            new ConnexionVue().setVisible(true); // rouvrir la page de connexion
        });
    }

    private void inscrire() { // méthode permettant l'inscription
        // récuperer tout les champs compléter
        String nom = champNom.getText().trim();
        String prenom = champPrenom.getText().trim();
        String email = champEmail.getText().trim();
        String ancien = champAncien.getText().trim();
        String motDePasse = new String(champMotDePasse.getPassword()).trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty() || ancien.isEmpty()) { // verifie que tout les champs sont remplis
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs."); // sinon affiche une fenêtre d'erreur
            return;
        }

        PatientDAO dao = new PatientDAO(); //créé un nouveau patient

        if (dao.emailExisteDeja(email)) { // verifie s'il existe déjà un utilistaeur avec cette adresse email
            JOptionPane.showMessageDialog(this, "Cet email est déjà utilisé."); // si oui alors fenêtre d'erreur
            return;
        }

        boolean success = dao.enregistrerPatient(nom, prenom, email, motDePasse, ancien); // enregistremment de l'utilisateur
        if (success) {
            JOptionPane.showMessageDialog(this, "Compte créé avec succès !"); // message en cas de succes
            dispose(); // ferme la page actuelle
            new ConnexionVue().setVisible(true); // ouvre la page de connexion
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la création du compte."); // erreur en cas d'échec
        }
    }

    private JPanel createInputRow(String labelText, JTextField field) { // méthode pour une ligne label + text
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(400, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        wrapper.add(label);
        wrapper.add(field);
        return wrapper;
    }

    private JButton createStyledButton(String text) { // méthode pour bouton stylisé
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 45));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        Color base = button.getBackground();
        Color hover = base.brighter();
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(base);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InscriptionVue().setVisible(true));
    }
}