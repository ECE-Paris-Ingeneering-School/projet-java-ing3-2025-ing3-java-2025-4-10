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

    private final RendezVousControleur controleur; // controleur de rendez-vous

    public HistoriqueVue(int IdUser) { // constructeur, prend l'id du user en parametre
        setTitle("Historique des rendez-vous"); // titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // ferme tout à la fermeture
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ouvrir en plein écran
        setLocationRelativeTo(null); // centrer

        this.controleur = new RendezVousControleur(); // création du controleur

        initialiserInterface(IdUser); // appel de la méthode pour construire l'interface
    }

    private void initialiserInterface(int IdUser) { // méthode pour dessiner la page
        JPanel fond = new JPanel(new GridBagLayout()); // fond bleu ciel
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel(); // panneau central blanc
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(900, 600)); // dimensions max

        JLabel titre = new JLabel("Historique de vos rendez-vous"); // titre
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        // création du tableau vide avec les colonnes
        String[] colonnes = {"Date", "Heure", "Spécialiste", "Lieu", "Motif"};
        tableModel = new DefaultTableModel(colonnes, 0);
        tableHistorique = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableHistorique); // barre défilement
        scrollPane.setPreferredSize(new Dimension(800, 250));
        scrollPane.setMaximumSize(new Dimension(800, 250));
        panel.add(scrollPane);

        boutonRetour = createStyledButton("Retour au menu"); // bouton retour
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);

        fond.add(panel); // ajout du panneau au centre
        setContentPane(fond);

        boutonRetour.addActionListener(e -> { // action bouton retour
            dispose();
            new MenuPrincipalVue("patient", IdUser).setVisible(true);
        });

        chargerHistorique(IdUser); // chargement de l'historique pour ce user
    }

    private void chargerHistorique(int IdUser) { // méthode pour remplir la table
        List<RendezVous> historique = controleur.getRendezVousParPatient(IdUser); // appel au controleur

        for (RendezVous rdv : historique) { // on parcourt les rendez-vous
            tableModel.addRow(new Object[]{
                    rdv.getDate(),
                    rdv.getHeure(),
                    rdv.getIdSpecialiste(), // à remplacer plus tard par le vrai nom du spécialiste
                    rdv.getIdLieu(), // à remplacer plus tard par le vrai nom du lieu
                    rdv.getMotif()
            });
        }
    }

    private JButton createStyledButton(String text) { // méthode pour créer un bouton stylisé
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(33, 150, 243)); // bleu
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(350, 45));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        return button;
    }

    public static void main(String[] args) { // pour tester seul
        SwingUtilities.invokeLater(() -> new HistoriqueVue(1).setVisible(true));
    }
}
