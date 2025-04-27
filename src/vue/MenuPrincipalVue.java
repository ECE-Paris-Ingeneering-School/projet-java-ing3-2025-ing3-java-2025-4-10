package vue; 

// importation des classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe MenuPrincipalVue
 * Permet d'afficher le menu principal de l'application.
 */
public class MenuPrincipalVue extends JFrame { // classe du menu principal qui hérite de jframe

    private JButton boutonPrendreRDV; // bouton pour prendre un rdv
    private JButton boutonHistorique; // bouton pour voir historique
    private JButton boutonStats; // bouton pour voir les stats (admin)
    private JButton boutonGestion; // bouton pour gérer (admin)
    private JButton boutonDeconnexion; // bouton pour se déconnecter
    private final int idUser; // id du user connecté
    private final String role; // rôle du user (patient ou admin)
    /**
     * Constructeur de la classe MenuPrincipalVue.
     * @param role le rôle de l'utilisateur (patient ou admin)
     * @param idUser l'identifiant de l'utilisateur
     */
    public MenuPrincipalVue(String role, int idUser) { // constructeur
        this.role = role;
        this.idUser = idUser;

        setTitle("Menu principal - " + role.toUpperCase()); // titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fermer appli si fermeture fenetre
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ouvrir en plein écran
        setLocationRelativeTo(null); // centrer

        initialiserInterface(idUser); // créer l'interface
    }
    /**
     * Méthode pour initialiser l'interface graphique.
     * Crée le fond, le panneau central, les boutons et les actions associées.
     */
    private void initialiserInterface(int idUser) { // méthode pour dessiner la page
        JPanel fond = new JPanel(new GridBagLayout()); // fond bleu ciel
        fond.setBackground(new Color(200, 225, 255));

        JPanel panelCentre = new JPanel(); // bloc blanc centré
        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));
        panelCentre.setBackground(Color.WHITE);
        panelCentre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(50, 80, 50, 80)
        ));
        panelCentre.setMaximumSize(new Dimension(600, 600));

        JLabel titre = new JLabel("Bienvenue sur le tableau de bord " + role); // titre de la page
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        panelCentre.add(titre);

        // boutons différents selon le rôle
        if (role.equalsIgnoreCase("patient")) {
            boutonPrendreRDV = createStyledButton("Prendre un rendez-vous");
            boutonHistorique = createStyledButton("Voir l'historique");

            panelCentre.add(boutonPrendreRDV);
            panelCentre.add(Box.createVerticalStrut(15)); // espace
            panelCentre.add(boutonHistorique);

            boutonPrendreRDV.addActionListener(e -> { // clique rdv
                dispose();
                new PriseRdvVue(idUser).setVisible(true);
            });

            boutonHistorique.addActionListener(e -> { // clique historique
                dispose();
                new HistoriqueVue(idUser).setVisible(true);
            });

        } else if (role.equalsIgnoreCase("admin")) {
            boutonGestion = createStyledButton("Espace d'administration");
            boutonStats = createStyledButton("Voir les statistiques");

            panelCentre.add(boutonGestion);
            panelCentre.add(Box.createVerticalStrut(15));
            panelCentre.add(boutonStats);

            boutonGestion.addActionListener(e -> { // clique gestion
                dispose();
                new AdminGestionVue().setVisible(true);
            });

            boutonStats.addActionListener(e -> { // clique stats
                dispose();
                new ReportingVue().setVisible(true);
            });
        }

        panelCentre.add(Box.createVerticalStrut(30)); // espace avant déconnexion
        boutonDeconnexion = createStyledButton("Se déconnecter"); // bouton déconnexion
        panelCentre.add(boutonDeconnexion);

        boutonDeconnexion.addActionListener(e -> { // clique déconnexion
            dispose();
            new ConnexionVue().setVisible(true);
        });

        fond.add(panelCentre); // ajout panel centre dans fond
        setContentPane(fond); // définir fond

        // barre de menu en haut
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(230, 240, 255)); // bleu clair

        for (String title : new String[]{"Accueil", "Prendre RDV", "Recherche", "Profil"}) {
            JMenu menu = new JMenu(title);
            menu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            menuBar.add(menu);
        }

        setJMenuBar(menuBar);
    }
    /**
     * Méthode pour créer un bouton stylisé.
     * @param text le texte du bouton
     * @return le bouton stylisé
     */
    private JButton createStyledButton(String text) { // méthode pour créer un bouton stylisé
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(33, 150, 243)); // fond bleu
        button.setForeground(Color.WHITE); // texte blanc
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // curseur main
        button.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(350, 50));

        Color base = button.getBackground();
        Color hover = base.brighter();

        button.addMouseListener(new MouseAdapter() { // effet survol
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(base);
            }
        });

        return button;
    }
}
