package vue;

import javax.swing.*;
import java.awt.*;

public class AdminGestionVue extends JFrame {

    private JButton boutonSpecialistes;
    private JButton boutonLieux;
    private JButton boutonRendezVous;
    private JButton boutonStats;
    private JButton boutonRetour;

    public AdminGestionVue() {
        setTitle("Espace d'administration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        boutonSpecialistes = new JButton("Gérer les spécialistes");
        boutonLieux = new JButton("Gérer les lieux");
        boutonRendezVous = new JButton("Gérer les rendez-vous");
        boutonStats = new JButton("Voir les statistiques");
        boutonRetour = new JButton("Retour au menu");

        panel.add(boutonSpecialistes);
        panel.add(boutonLieux);
        panel.add(boutonRendezVous);
        panel.add(boutonStats);
        panel.add(boutonRetour);

        add(panel);

        // Actions reliées aux vues réelles
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
            new MenuPrincipalVue("admin").setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminGestionVue().setVisible(true));
    }
}
