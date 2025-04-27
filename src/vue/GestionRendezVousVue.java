package vue;

import controleur.RendezVousControleur; // importation du controleur pour les rendez-vous
import modele.RendezVous; // importation du modele rendezvous

import javax.swing.*; // composants swing
import javax.swing.table.DefaultTableModel; // modèle de table pour les données
import java.awt.*; // gestion interface graphique
import java.util.List; // gestion des listes

public class GestionRendezVousVue extends JFrame { // classe gestionrendezvousvue qui hérite de jframe

    private JTable tableRendezVous; // tableau pour afficher les rendez-vous
    private DefaultTableModel tableModel; // modèle de données pour la table
    private JButton boutonRetour; // bouton retour

    private final RendezVousControleur controleur; // controleur pour gérer les rendez-vous

    public GestionRendezVousVue() { // constructeur
        setTitle("Gestion des rendez-vous"); // titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // ferme complètement l'appli
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fenetre plein écran
        setLocationRelativeTo(null); // centre la fenetre

        this.controleur = new RendezVousControleur(); // instanciation du controleur

        initialiserInterface(); // appel pour construire l'interface
    }

    private void initialiserInterface() { // méthode pour construire l'interface
        JPanel fond = new JPanel(new GridBagLayout()); // fond de la page
        fond.setBackground(new Color(200, 225, 255)); // couleur bleu ciel

        JPanel panel = new JPanel(); // bloc central
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // disposition verticale
        panel.setBackground(Color.WHITE); // fond blanc
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60)); // marges internes
        panel.setMaximumSize(new Dimension(900, 700)); // taille maximale

        JLabel titre = new JLabel("Gestion des rendez-vous"); // titre
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24)); // police du titre
        titre.setAlignmentX(Component.CENTER_ALIGNMENT); // centrer le titre
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0)); // espacement en bas
        panel.add(titre);

        tableRendezVous = new JTable(); // création de la table
        JScrollPane scrollPane = new JScrollPane(tableRendezVous); // barre de défilement pour la table
        scrollPane.setPreferredSize(new Dimension(800, 300)); // taille préférée
        scrollPane.setMaximumSize(new Dimension(800, 300)); // taille maximale
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20)); // espacement vertical

        boutonRetour = createStyledButton("Retour"); // bouton retour
        panel.add(boutonRetour);

        fond.add(panel); // ajout du panel dans le fond
        setContentPane(fond); // placement du fond dans la fenetre

        boutonRetour.addActionListener(e -> { // action retour
            dispose();
            new AdminGestionVue().setVisible(true);
        });

        chargerRendezVous(); // charger les rendez-vous dans la table
    }

    private void chargerRendezVous() { // méthode pour charger les rendez-vous
        List<RendezVous> liste = controleur.getTousLesRendezVous(); // récupération de tous les rendez-vous

        String[] colonnes = {"ID", "Patient", "Spécialiste", "Lieu", "Date", "Heure", "Note"}; // titres des colonnes
        tableModel = new DefaultTableModel(colonnes, 0); // modèle vide au départ

        for (RendezVous rdv : liste) { // pour chaque rendez-vous
            Object[] ligne = {
                    rdv.getId(),
                    rdv.getIdPatient(),
                    rdv.getIdSpecialiste(),
                    rdv.getIdLieu(),
                    rdv.getDate(),
                    rdv.getHeure(),
                    rdv.getMotif()
            };
            tableModel.addRow(ligne); // ajouter la ligne au modèle
        }

        tableRendezVous.setModel(tableModel); // associer le modèle à la table
    }

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

    public static void main(String[] args) { // méthode pour tester cette page indépendamment
        SwingUtilities.invokeLater(() -> new GestionRendezVousVue().setVisible(true));
    }
}
