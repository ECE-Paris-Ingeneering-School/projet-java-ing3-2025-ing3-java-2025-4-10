package vue; // package vue

// on importe les classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminGestionVue extends JFrame { // classe admingestionvue qui hérite de jframe

    // déclaration des boutons visibles
    private JButton boutonSpecialistes;
    private JButton boutonLieux;
    private JButton boutonRendezVous;
    private JButton boutonStats;
    private JButton boutonRetour;

    public AdminGestionVue() { // constructeur
        setTitle("Espace d'administration"); // titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // ferme l'appli si on ferme la fenetre
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fenetre en plein ecran
        setLocationRelativeTo(null); // centre la fenetre

        initialiserInterface(); // appel pour construire l'interface
    }

    private void initialiserInterface() { // construire l'interface
        // création du fond bleu ciel
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        // bloc central blanc
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60) // padding autour du bloc
        ));
        panel.setMaximumSize(new Dimension(500, 500));

        // création du titre
        JLabel titre = new JLabel("Espace d'administration");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        // création des boutons
        boutonSpecialistes = createStyledButton("Gérer les spécialistes");
        boutonLieux = createStyledButton("Gérer les lieux");
        boutonRendezVous = createStyledButton("Gérer les rendez-vous");
        boutonStats = createStyledButton("Voir les statistiques");
        boutonRetour = createStyledButton("Retour au menu");

        // on ajoute les boutons séparés par des espacements
        panel.add(boutonSpecialistes);
        panel.add(Box.createVerticalStrut(15));
        panel.add(boutonLieux);
        panel.add(Box.createVerticalStrut(15));
        panel.add(boutonRendezVous);
        panel.add(Box.createVerticalStrut(15));
        panel.add(boutonStats);
        panel.add(Box.createVerticalStrut(25));
        panel.add(boutonRetour);

        // on place le bloc blanc au centre du fond bleu
        fond.add(panel);
        setContentPane(fond);

        // actions sur les boutons
        boutonSpecialistes.addActionListener(e -> {
            dispose();
            new GestionSpecialistesVue().setVisible(true);
        });

        boutonLieux.addActionListener(e -> {
            dispose();
            new GestionLieuxVue().setVisible(true);
        });

        boutonRendezVous.addActionListener(e -> {
            dispose();
            new GestionRendezVousVue().setVisible(true);
        });

        boutonStats.addActionListener(e -> {
            dispose();
            new ReportingVue().setVisible(true);
        });

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("admin", 1).setVisible(true); // on passe admin et l'id 1
        });
    }

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

        // gestion des couleurs de survol
        Color base = button.getBackground();
        Color hover = base.brighter();

        // effet visuel au passage souris
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

    // pour tester indépendamment cette fenêtre
    /**
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminGestionVue().setVisible(true));
    }
    */
}
