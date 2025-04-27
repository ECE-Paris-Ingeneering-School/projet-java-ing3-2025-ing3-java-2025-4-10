package vue;

import controleur.SpecialisteControleur; // import du controleur pour gérer les spécialistes
import modele.Specialiste; // import du modèle spécialiste

import javax.swing.*; // composants swing
import javax.swing.table.DefaultTableModel; // modèle pour tableau
import java.awt.*; // interface graphique
import java.util.List; // listes
/**
 * Classe de la vue pour la gestion des spécialistes.
 * Permet d'ajouter, supprimer et afficher les spécialistes.
 */
public class GestionSpecialistesVue extends JFrame { // classe qui hérite de jframe

    private JTextField champNom, champPrenom, champEmail, champSpecialite; // champs texte
    private JButton boutonAjouter, boutonSupprimer, boutonRetour; // boutons
    private JTable tableSpecialistes; // tableau pour afficher les spécialistes
    private DefaultTableModel tableModel; // modèle de données pour le tableau

    private final SpecialisteControleur controleur; // controleur spécialité

    /**
     * Constructeur de la classe GestionSpecialistesVue.
     */
    public GestionSpecialistesVue() { // constructeur
        setTitle("Gestion des spécialistes"); // titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fermer l'application
        setExtendedState(JFrame.MAXIMIZED_BOTH); // plein écran
        setLocationRelativeTo(null); // centrer

        this.controleur = new SpecialisteControleur(); // création du controleur
        initialiserInterface(); // création de l'interface
    }
    /**
     * Méthode pour initialiser l'interface graphique.
     * Crée le fond, le panneau central, les champs de saisie et les boutons.
     */
    private void initialiserInterface() { // méthode pour construire l'interface
        JPanel fond = new JPanel(new GridBagLayout()); // fond bleu ciel
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel(); // panneau central blanc
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60)); // marges internes
        panel.setMaximumSize(new Dimension(900, 750)); // limite de taille

        JLabel titre = new JLabel("Gestion des spécialistes"); // titre de la page
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titre);

        // champs pour remplir les infos du spécialiste
        champNom = new JTextField();
        champPrenom = new JTextField();
        champEmail = new JTextField();
        champSpecialite = new JTextField();

        panel.add(createInput("Nom :", champNom));
        panel.add(createInput("Prénom :", champPrenom));
        panel.add(createInput("Email :", champEmail));
        panel.add(createInput("Spécialité :", champSpecialite));
        panel.add(Box.createVerticalStrut(20)); // espace vertical

        // création des boutons
        boutonAjouter = createStyledButton("Ajouter spécialiste");
        boutonSupprimer = createStyledButton("Supprimer spécialiste");
        boutonRetour = createStyledButton("Retour");

        // ajout des boutons dans le panel
        panel.add(boutonAjouter);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonSupprimer);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);
        panel.add(Box.createVerticalStrut(30));

        tableSpecialistes = new JTable(); // création du tableau
        JScrollPane scrollPane = new JScrollPane(tableSpecialistes); // barre de défilement
        scrollPane.setPreferredSize(new Dimension(800, 200));
        scrollPane.setMaximumSize(new Dimension(800, 200));
        panel.add(scrollPane);

        fond.add(panel); // placer le panneau central sur le fond
        setContentPane(fond);

        // actions des boutons
        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });

        boutonAjouter.addActionListener(e -> ajouterSpecialiste());

        boutonSupprimer.addActionListener(e -> supprimerSpecialiste());

        chargerSpecialistes(); // charger les spécialistes au démarrage
    }
    /**
     * Méthode pour charger tous les spécialistes dans le tableau.
     * Récupère la liste des spécialistes depuis le controleur et les affiche dans le tableau.
     */
    private void chargerSpecialistes() { // méthode pour charger les spécialistes
        List<Specialiste> liste = controleur.getTousLesSpecialistes(); // récupération depuis le controleur

        String[] colonnes = {"ID", "Nom", "Prénom", "Email", "Spécialité"}; // entêtes de colonnes
        tableModel = new DefaultTableModel(colonnes, 0); // création du modèle

        for (Specialiste s : liste) { // pour chaque spécialiste
            Object[] ligne = {
                    s.getId(),
                    s.getNom(),
                    s.getPrenom(),
                    s.getEmail(),
                    s.getSpecialite(),
            };
            tableModel.addRow(ligne); // ajouter dans le tableau
        }

        tableSpecialistes.setModel(tableModel); // attacher le modèle à la table
    }
    /**
     * Méthode pour ajouter un spécialiste.
     * Récupère les données des champs, crée un objet Spécialiste et l'ajoute via le controleur.
     */
    private void ajouterSpecialiste() { // ajouter un spécialiste
        String nom = champNom.getText().trim();
        String prenom = champPrenom.getText().trim();
        String email = champEmail.getText().trim();
        String specialite = champSpecialite.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || specialite.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        Specialiste specialiste = new Specialiste(0, nom, prenom, email, specialite); // créer un objet spécialite

        if (controleur.ajouterSpecialiste(specialiste)) { // appel au controleur
            JOptionPane.showMessageDialog(this, "Spécialiste ajouté avec succès.");
            viderChamps();
            chargerSpecialistes();
        } else {
            JOptionPane.showMessageDialog(this, "Échec de l'ajout.");
        }
    }
    /**
     * Méthode pour supprimer un spécialiste.
     * Récupère l'ID du spécialiste sélectionné et le supprime via le controleur.
     */
    private void supprimerSpecialiste() { // méthode pour supprimer un spécialiste
        int ligneSelectionnee = tableSpecialistes.getSelectedRow();

        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un spécialiste à supprimer.");
            return;
        }

        int id = (int) tableModel.getValueAt(ligneSelectionnee, 0); // récupérer l'id

        if (controleur.supprimerSpecialisteParId(id)) { // suppression par controleur
            JOptionPane.showMessageDialog(this, "Spécialiste supprimé.");
            chargerSpecialistes();
        } else {
            JOptionPane.showMessageDialog(this, "Échec de la suppression.");
        }
    }
    /**
     * Méthode pour vider les champs de saisie.
     * Réinitialise les champs texte à une chaîne vide.
     */
    private void viderChamps() { // méthode pour vider les champs
        champNom.setText("");
        champPrenom.setText("");
        champEmail.setText("");
        champSpecialite.setText("");
    }
    /**
     * Méthode pour créer un champ de saisie avec un label.
     * @param labelText le texte du label
     * @param champ le champ de saisie
     * @return le panneau contenant le label et le champ
     */
    private JPanel createInput(String labelText, JTextField champ) { // méthode pour créer une ligne texte
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(500, 60));

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
     * @param text le texte du bouton
     * @return le bouton stylisé
     */
    private JButton createStyledButton(String text) { // méthode pour créer un bouton stylisé
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(350, 45));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        return button;
    }
}
