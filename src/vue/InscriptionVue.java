package vue; 

// importation des classes nécessaires
import controleur.RendezVousControleur;
import modele.RendezVous;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoriqueVue extends JFrame { // classe historique vue qui hérite de jframe

    private JTable tableHistorique; // tableau pour afficher l'historique
    private JButton boutonRetour; // bouton retour
    private DefaultTableModel tableModel; // modèle pour remplir le tableau

    private final RendezVousControleur controleur; // controleur pour récupérer les rdv

    public HistoriqueVue(int IdUser) { // constructeur, prend l'id du user
        setTitle("Historique des rendez-vous"); // titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fermer l'appli si on ferme la fenêtre
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ouvrir en plein écran
        setLocationRelativeTo(null); // centrer la fenêtre

        this.controleur = new RendezVousControleur(); // création du controleur

        initialiserInterface(IdUser); // création de l'interface
    }

    private void initialiserInterface(int IdUser) { // méthode pour dessiner la page
        JPanel fond = new JPanel(new GridBagLayout()); // fond bleu ciel
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel(); // panneau blanc au centre
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60)); // marges internes
        panel.setMaximumSize(new Dimension(900, 600)); // taille max du bloc blanc

        JLabel titre = new JLabel("Historique de vos rendez-vous"); // titre principal
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT); // centré horizontalement
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        // création du tableau vide
        String[] colonnes = {"Date", "Heure", "Spécialiste", "Lieu", "Motif"}; // titres colonnes
        tableModel = new DefaultTableModel(colonnes, 0); // modèle vide
        tableHistorique = new JTable(tableModel); // création du tableau

        JScrollPane scrollPane = new JScrollPane(tableHistorique); // scroll si déborde
        scrollPane.setPreferredSize(new Dimension(800, 250));
        scrollPane.setMaximumSize(new Dimension(800, 250));
        panel.add(scrollPane); // on ajoute le tableau au panneau

        boutonRetour = createStyledButton("Retour au menu"); // bouton retour au menu
        panel.add(Box.createVerticalStrut(20)); // espace
        panel.add(boutonRetour); // ajout du bouton

        fond.add(panel); // on ajoute tout au fond
        setContentPane(fond); // on définit le fond

        boutonRetour.addActionListener(e -> { // action retour
            dispose(); // fermer la fenêtre actuelle
            new MenuPrincipalVue("patient", IdUser).setVisible(true); // rouvrir menu principal patient
        });

        chargerHistorique(IdUser); // on charge les rendez-vous du patient
    }

    private void chargerHistorique(int IdUser) { // méthode pour charger les données dans la table
        List<RendezVous> historique = controleur.getRendezVousParPatient(IdUser); // récupère historique

        for (RendezVous rdv : historique) { // pour chaque rdv
            tableModel.addRow(new Object[]{
                    rdv.getDate(), // date du rdv
                    rdv.getHeure(), // heure du rdv
                    rdv.getIdSpecialiste(), // id spécialiste (à améliorer pour afficher son nom)
                    rdv.getIdLieu(), // id lieu (à améliorer aussi)
                    rdv.getMotif() // motif du rendez-vous
            });
        }
    }

    private JButton createStyledButton(String text) { // méthode pour créer un bouton stylisé
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // police normale
        button.setBackground(new Color(33, 150, 243)); // fond bleu
        button.setForeground(Color.WHITE); // texte blanc
        button.setFocusPainted(false); // pas de contour focus
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // curseur main
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // centré
        button.setMaximumSize(new Dimension(350, 45)); // taille max
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20)); // marges internes
        return button;
    }

    public static void main(String[] args) { // méthode main pour tester seul
        SwingUtilities.invokeLater(() -> new HistoriqueVue(1).setVisible(true));
    }
}
