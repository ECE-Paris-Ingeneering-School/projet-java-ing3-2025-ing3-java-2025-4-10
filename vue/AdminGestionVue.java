package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminGestionVue extends JFrame {

    private JButton boutonSpecialistes;
    private JButton boutonLieux;
    private JButton boutonRendezVous;
    private JButton boutonStats;
    private JButton boutonRetour;

    public AdminGestionVue() {
        setTitle("Espace d'administration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        // Fond bleu ciel
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        // Bloc central blanc
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)
        ));
        panel.setMaximumSize(new Dimension(500, 500));

        JLabel titre = new JLabel("Espace d'administration");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        // Boutons
        boutonSpecialistes = createStyledButton("👨‍⚕️ Gérer les spécialistes");
        boutonLieux = createStyledButton("📍 Gérer les lieux");
        boutonRendezVous = createStyledButton("📅 Gérer les rendez-vous");
        boutonStats = createStyledButton("📊 Voir les statistiques");
        boutonRetour = createStyledButton("↩️ Retour au menu");

        panel.add(boutonSpecialistes);
        panel.add(Box.createVerticalStrut(15));
        panel.add(boutonLieux);
        panel.add(Box.createVerticalStrut(15));
        panel.add(boutonRendezVous);
        panel.add(Box.createVerticalStrut(15));
        panel.add(boutonStats);
        panel.add(Box.createVerticalStrut(25));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        // Actions
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

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(350, 45));
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
        SwingUtilities.invokeLater(() -> new AdminGestionVue().setVisible(true));
    }
}
