package vue;

import controleur.LieuControleur; // importation du controleur pour les lieux
import modele.Lieu; // importation de la classe lieu

import javax.swing.*; // composants swing
import javax.swing.table.DefaultTableModel; // modèle pour la table
import java.awt.*; // gestion de l'interface graphique
import java.util.List; // gestion des listes
/**
 * Classe de la vue pour la gestion des lieux.
 * Permet d'ajouter, supprimer et afficher les lieux.
 */
public class GestionLieuxVue extends JFrame { // classe gestionlieuxvue qui hérite de jframe

    private JTextField champAdresse, champVille, champCodePostal; // champs de saisie
    private JButton boutonAjouter, boutonSupprimer, boutonRetour; // boutons
    private JTable tableLieux; // table pour afficher les lieux
    private DefaultTableModel tableModel; // modèle de données pour la table

    private final LieuControleur controleur; // controleur pour gérer les lieux

    public GestionLieuxVue() { // constructeur
        setTitle("Gestion des lieux"); // titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fermeture complète à la croix
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ouverture en plein ecran
        setLocationRelativeTo(null); // centrer la fenetre

        this.controleur = new LieuControleur(); // création du controleur

        initialiserInterface(); // appel pour construire l'interface
    }

    private void initialiserInterface() { // construire l'interface
        JPanel fond = new JPanel(new GridBagLayout()); // fond en grille
        fond.setBackground(new Color(200, 225, 255)); // fond bleu ciel

        JPanel panel = new JPanel(); // bloc central
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // disposition verticale
        panel.setBackground(Color.WHITE); // fond blanc
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60)); // marges internes
        panel.setMaximumSize(new Dimension(900, 700)); // taille max

        JLabel titre = new JLabel("Gestion des lieux"); // titre
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24)); // police du titre
        titre.setAlignmentX(Component.CENTER_ALIGNMENT); // centré
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // espacement bas
        panel.add(titre);

        // champs de saisie
        champAdresse = new JTextField();
        champVille = new JTextField();
        champCodePostal = new JTextField();

        panel.add(createInput("Adresse :", champAdresse));
        panel.add(createInput("Ville :", champVille));
        panel.add(createInput("Code postal :", champCodePostal));
        panel.add(Box.createVerticalStrut(20)); // espace

        // boutons
        boutonAjouter = createStyledButton("Ajouter lieu");
        boutonSupprimer = createStyledButton("Supprimer lieu");
        boutonRetour = createStyledButton("Retour");

        panel.add(boutonAjouter);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonSupprimer);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);
        panel.add(Box.createVerticalStrut(30));

        // table pour afficher les lieux
        tableLieux = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableLieux);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        scrollPane.setMaximumSize(new Dimension(800, 200));
        panel.add(scrollPane);

        fond.add(panel); // ajout du bloc dans le fond
        setContentPane(fond); // placement du fond dans la fenetre

        // actions sur les boutons
        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });

        boutonAjouter.addActionListener(e -> ajouterLieu());
        boutonSupprimer.addActionListener(e -> supprimerLieu());

        chargerLieux(); // afficher les lieux existants
    }

    private void chargerLieux() { // remplir la table
        List<Lieu> lieux = controleur.getTousLesLieux(); // récupération des lieux

        String[] colonnes = {"ID", "Adresse", "Ville", "Code postal"}; // titres des colonnes
        tableModel = new DefaultTableModel(colonnes, 0); // création du modèle vide

        for (Lieu l : lieux) { // pour chaque lieu
            Object[] ligne = {
                    l.getId(),
                    l.getAdresse(),
                    l.getVille(),
                    l.getCodePostal()
            };
            tableModel.addRow(ligne); // ajout dans le modèle
        }

        tableLieux.setModel(tableModel); // associer le modèle à la table
    }

    private void ajouterLieu() { // ajouter un lieu
        String adresse = champAdresse.getText().trim();
        String ville = champVille.getText().trim();
        String codePostal = champCodePostal.getText().trim();

        if (adresse.isEmpty() || ville.isEmpty() || codePostal.isEmpty()) { // vérification champs
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        Lieu lieu = new Lieu(0, adresse, ville, codePostal); // création du lieu

        if (controleur.ajouterLieu(lieu)) { // tentative d'ajout
            JOptionPane.showMessageDialog(this, "Lieu ajouté avec succès.");
            viderChamps();
            chargerLieux(); // rafraichir la table
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout.");
        }
    }

    private void supprimerLieu() { // supprimer un lieu
        int ligneSelectionnee = tableLieux.getSelectedRow(); // récupérer ligne sélectionnée

        if (ligneSelectionnee == -1) { // si rien sélectionné
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un lieu à supprimer.");
            return;
        }

        int id = (int) tableModel.getValueAt(ligneSelectionnee, 0); // récupérer l'id

        if (controleur.supprimerLieuParId(id)) { // tentative de suppression
            JOptionPane.showMessageDialog(this, "Lieu supprimé.");
            chargerLieux(); // rafraichir la table
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression.");
        }
    }

    private void viderChamps() { // vider tous les champs
        champAdresse.setText("");
        champVille.setText("");
        champCodePostal.setText("");
    }

    private JPanel createInput(String labelText, JTextField champ) { // créer un label + champ alignés
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

    private JButton createStyledButton(String text) { // créer un bouton stylisé
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

    public static void main(String[] args) { // méthode pour tester la vue seule
        SwingUtilities.invokeLater(() -> new GestionLieuxVue().setVisible(true));
    }
}
