package vue;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalVue extends JFrame {

    private JButton boutonPrendreRDV;
    private JButton boutonHistorique;
    private JButton boutonStats;
    private JButton boutonGestion;
    private JButton boutonDeconnexion;

    public MenuPrincipalVue(String role) {
        setTitle("Menu principal - " + role);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initialiserInterface(role);
    }

    private void initialiserInterface(String role) {
        setLayout(new BorderLayout());

        // === BARRE DE MENU EN HAUT ===
        JMenuBar menuBar = new JMenuBar();

        JMenu accueilMenu = new JMenu("Accueil");
        JMenu rdvMenu = new JMenu("Prendre RDV");
        JMenu rechercheMenu = new JMenu("Recherche");
        JMenu profilMenu = new JMenu("Profil");

        menuBar.add(accueilMenu);
        menuBar.add(rdvMenu);
        menuBar.add(rechercheMenu);
        menuBar.add(profilMenu);

        setJMenuBar(menuBar);

        // === CONTENU CENTRAL ===
        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));
        panelCentre.setBorder(BorderFactory.createEmptyBorder(100, 400, 100, 400));
        panelCentre.setBackground(Color.WHITE);

        JLabel titre = new JLabel("Bienvenue sur le tableau de bord " + role);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setFont(new Font("Arial", Font.BOLD, 28));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        panelCentre.add(titre);

        // Boutons en fonction du rôle
        if (role.equalsIgnoreCase("patient")) {
            boutonPrendreRDV = createButton("Prendre un rendez-vous");
            boutonHistorique = createButton("Voir l'historique");

            panelCentre.add(boutonPrendreRDV);
            panelCentre.add(Box.createVerticalStrut(10));
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
            boutonGestion = createButton("Espace d'administration");
            boutonStats = createButton("Voir les statistiques");

            panelCentre.add(boutonGestion);
            panelCentre.add(Box.createVerticalStrut(10));
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

        panelCentre.add(Box.createVerticalStrut(20));
        boutonDeconnexion = createButton("Se déconnecter");
        panelCentre.add(boutonDeconnexion);

        boutonDeconnexion.addActionListener(e -> {
            dispose();
            new ConnexionVue().setVisible(true);
        });

        add(panelCentre, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(300, 50));
        button.setMaximumSize(new Dimension(300, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipalVue("patient").setVisible(true));
        // Pour tester admin :
        // SwingUtilities.invokeLater(() -> new MenuPrincipalVue("admin").setVisible(true));
    }
}
