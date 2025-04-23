package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPrincipalVue extends JFrame {

    private JButton boutonPrendreRDV;
    private JButton boutonHistorique;
    private JButton boutonStats;
    private JButton boutonGestion;
    private JButton boutonDeconnexion;

    public MenuPrincipalVue(String role) {
        setTitle("Menu principal - " + role.toUpperCase());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initialiserInterface(role);
    }

    private void initialiserInterface(String role) {
        // === FOND BLEU CIEL ===
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255)); // 🎨 BLEU CIEL

        // === CONTENU CENTRAL ENCADRÉ ===
        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));
        panelCentre.setBackground(Color.WHITE);
        panelCentre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(50, 80, 50, 80)
        ));
        panelCentre.setMaximumSize(new Dimension(600, 600));

        // === TITRE ===
        JLabel titre = new JLabel("Bienvenue sur le tableau de bord " + role);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        panelCentre.add(titre);

        // === BOUTONS SELON LE RÔLE ===
        if (role.equalsIgnoreCase("patient")) {
            boutonPrendreRDV = createStyledButton("🗓️ Prendre un rendez-vous");
            boutonHistorique = createStyledButton("📄 Voir l'historique");

            panelCentre.add(boutonPrendreRDV);
            panelCentre.add(Box.createVerticalStrut(15));
            panelCentre.add(boutonHistorique);

            boutonPrendreRDV.addActionListener(e -> {
                dispose();
                new PriseRdvVue().setVisible(true);
            });

            boutonHistorique.addActionListener(e -> {
                dispose();
                new HistoriqueVue().setVisible(true);
            });

        } else if (role.equalsIgnoreCase("admin")) {
            boutonGestion = createStyledButton("⚙️ Espace d'administration");
            boutonStats = createStyledButton("📊 Voir les statistiques");

            panelCentre.add(boutonGestion);
            panelCentre.add(Box.createVerticalStrut(15));
            panelCentre.add(boutonStats);

            boutonGestion.addActionListener(e -> {
                dispose();
                new AdminGestionVue().setVisible(true);
            });

            boutonStats.addActionListener(e -> {
                dispose();
                new ReportingVue().setVisible(true);
            });
        }

        panelCentre.add(Box.createVerticalStrut(30));
        boutonDeconnexion = createStyledButton("🚪 Se déconnecter");
        panelCentre.add(boutonDeconnexion);

        boutonDeconnexion.addActionListener(e -> {
            dispose();
            new ConnexionVue().setVisible(true);
        });

        // CENTRAGE DU CONTENU
        fond.add(panelCentre);
        setContentPane(fond);

        // === BARRE DE MENU EN HAUT ===
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(230, 240, 255)); // un bleu encore plus pâle

        for (String title : new String[]{"Accueil", "Prendre RDV", "Recherche", "Profil"}) {
            JMenu menu = new JMenu(title);
            menu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            menuBar.add(menu);
        }

        setJMenuBar(menuBar);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(350, 50));

        // Hover effect
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
        SwingUtilities.invokeLater(() -> new MenuPrincipalVue("patient").setVisible(true));
        // Pour tester admin :
        // SwingUtilities.invokeLater(() -> new MenuPrincipalVue("admin").setVisible(true));
    }
}
