package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistoriqueVue extends JFrame {

    private JTable tableHistorique;
    private JButton boutonRetour;

    public HistoriqueVue() {
        setTitle("Historique des rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        // === Fond bleu ciel ===
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        // === Panel blanc central ===
        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));
        panelCentre.setBackground(Color.WHITE);
        panelCentre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        panelCentre.setMaximumSize(new Dimension(900, 600));

        JLabel titre = new JLabel("Historique des rendez-vous");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelCentre.add(titre);

        // === Données et colonnes ===
        String[] colonnes = {"Date", "Heure", "Spécialiste", "Lieu", "Note"};

        Object[][] donnees = {
                {"15/04/2024", "14:00", "Dr. Dupont", "Paris", "Très bien"},
                {"02/03/2024", "10:30", "Dr. Martin", "Lyon", "Correct"},
                {"18/01/2024", "09:00", "Dr. Karim", "Marseille", ""},
                {"12/05/2024", "11:00", "Dr. Lefevre", "Toulouse", "Excellent"},
                {"07/06/2024", "08:30", "Dr. Moreau", "Nice", "Parfait"},
                {"03/07/2024", "15:00", "Dr. Petit", "Nantes", "Satisfait"},
                {"19/07/2024", "17:00", "Dr. Laurent", "Strasbourg", "Retard"},
                {"25/08/2024", "10:00", "Dr. Henry", "Bordeaux", "Très bien"},
                {"05/09/2024", "09:30", "Dr. Faure", "Lille", "Bien"},
                {"11/09/2024", "13:00", "Dr. Arnaud", "Rennes", "Correct"},
                {"15/10/2024", "14:30", "Dr. Chevalier", "Grenoble", "Moyen"},
                {"28/10/2024", "11:30", "Dr. Gauthier", "Dijon", "Bon accueil"},
                {"06/11/2024", "12:00", "Dr. Masson", "Toulon", "Satisfait"},
                {"10/11/2024", "16:00", "Dr. Noël", "Saint-Étienne", "Super"},
                {"15/11/2024", "08:00", "Dr. Perrot", "Clermont-Ferrand", "Très bon"},
                {"21/11/2024", "15:30", "Dr. Vidal", "Reims", "RAS"},
                {"01/12/2024", "14:00", "Dr. Roux", "Amiens", ""},
                {"08/12/2024", "10:00", "Dr. Lucas", "Limoges", "Bien reçu"},
                {"14/12/2024", "13:30", "Dr. Marchand", "Annecy", "Agréable"},
                {"20/12/2024", "09:00", "Dr. Blanchard", "Tours", "Top"}
        };

        DefaultTableModel model = new DefaultTableModel(donnees, colonnes);
        tableHistorique = new JTable(model);
        tableHistorique.setRowHeight(28);
        tableHistorique.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tableHistorique.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHistorique.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tableHistorique);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setMaximumSize(new Dimension(800, 400));

        panelCentre.add(scrollPane);
        panelCentre.add(Box.createVerticalStrut(30));

        // === Bouton Retour ===
        boutonRetour = createStyledButton("↩️ Retour au menu");
        panelCentre.add(boutonRetour);

        fond.add(panelCentre);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient").setVisible(true);
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
        button.setMaximumSize(new Dimension(250, 45));
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
        SwingUtilities.invokeLater(() -> new HistoriqueVue().setVisible(true));
    }
}
