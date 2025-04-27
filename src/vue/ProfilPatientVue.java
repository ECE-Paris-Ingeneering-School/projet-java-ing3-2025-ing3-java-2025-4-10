package vue; 

// importation des classes nécessaires
import dao.PatientDAO;
import modele.Patient;

import javax.swing.*;
import java.awt.*;

/**
 * Classe ProfilPatientVue
 * Permet d'afficher et de modifier le profil d'un patient.
 */
public class ProfilPatientVue extends JFrame { // classe profil patient qui hérite de jframe

    private final int idUser; // id du patient connecté
    private JTextField champNom, champPrenom; // champs texte pour nom et prénom
    private JPasswordField champMotDePasse; // champ mot de passe
    private JButton boutonModifier, boutonRetour; // boutons
    /**
     * Constructeur de la classe ProfilPatientVue.
     * @param idUser id de l'utilisateur
     */
    public ProfilPatientVue(int idUser) { // constructeur avec id user
        this.idUser = idUser;

        setTitle("Mon profil"); // titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fermer toute l'application à la fermeture
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ouvrir en plein écran
        setLocationRelativeTo(null); // centrer

        initialiserInterface(); // construire l'interface
    }
    /**
     * Méthode pour initialiser l'interface graphique.
     * Crée le fond, le panneau central, les champs de saisie et les boutons.
     */
    private void initialiserInterface() { // méthode pour créer l'interface
        JPanel fond = new JPanel(new GridBagLayout()); // fond bleu ciel
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel(); // panneau central blanc
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(500, 500)); // dimensions maximales

        JLabel titre = new JLabel("Mes informations"); // titre
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titre);
        panel.add(Box.createVerticalStrut(30)); // espace vertical

        // création des champs de saisie
        champNom = new JTextField();
        champPrenom = new JTextField();
        champMotDePasse = new JPasswordField();

        panel.add(createInput("Nom :", champNom));
        panel.add(createInput("Prénom :", champPrenom));
        panel.add(createInput("Nouveau mot de passe :", champMotDePasse));

        panel.add(Box.createVerticalStrut(20));

        boutonModifier = createStyledButton("Enregistrer"); // bouton enregistrer
        panel.add(boutonModifier);
        panel.add(Box.createVerticalStrut(10));
        boutonRetour = createStyledButton("Retour"); // bouton retour
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> { // action retour
            dispose();
            new MenuPrincipalVue("patient", idUser).setVisible(true);
        });

        boutonModifier.addActionListener(e -> modifierInfos()); // action enregistrer

        chargerDonnees(); // chargement des infos du patient
    }
    /**
     * Méthode pour charger les données du patient.
     * Récupère les informations du patient depuis la base de données et les affiche dans les champs.
     */
    private void chargerDonnees() { // méthode pour charger les données du patient
        Patient p = new PatientDAO().recupererInfosPatient(idUser);
        if (p != null) {
            champNom.setText(p.getNom());
            champPrenom.setText(p.getPrenom());
        }
    }
    /**
     * Méthode pour modifier les informations du patient.
     * Vérifie si tous les champs sont remplis et met à jour les informations dans la base de données.
     */
    private void modifierInfos() { // méthode pour modifier les infos du patient
        String nom = champNom.getText().trim();
        String prenom = champPrenom.getText().trim();
        String mdp = new String(champMotDePasse.getPassword()).trim();

        if (nom.isEmpty() || prenom.isEmpty() || mdp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        boolean ok = new PatientDAO().modifierInfosPatient(idUser, nom, prenom, mdp);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Profil mis à jour !");
            dispose();
            new MenuPrincipalVue("patient", idUser).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.");
        }
    }
    /**
     * Méthode pour créer un champ de saisie avec une étiquette.
     * @param labelText texte de l'étiquette
     * @param champ composant à ajouter
     * @return JPanel contenant le label et le champ
     */
    private JPanel createInput(String labelText, JComponent champ) { // méthode pour créer un champ avec label
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(400, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        champ.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        champ.setAlignmentX(Component.LEFT_ALIGNMENT);

        wrapper.add(label);
        wrapper.add(champ);
        return wrapper;
    }
    /**
     * Méthode pour créer un bouton stylisé.
     * @param text texte du bouton
     * @return JButton stylisé
     */
    private JButton createStyledButton(String text) { // méthode pour créer un bouton stylisé
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 45));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        return button;
    }
}
