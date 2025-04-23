package vue;

import dao.RendezVousDAO;
import modele.RendezVous;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionRendezVousVue extends JFrame {

    private JTable tableRdv;
    private JButton boutonRetour;
    private DefaultTableModel model;

    public GestionRendezVousVue() {
        setTitle("Gestion des rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(1000, 500));

        JLabel titre = new JLabel("Tous les rendez-vous enregistrés");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titre);
        panel.add(Box.createVerticalStrut(30));

        // === TABLE ===
        String[] colonnes = {"ID", "Date", "Heure", "ID Patient", "ID Spécialiste", "ID Lieu", "Note"};
        model = new DefaultTableModel(colonnes, 0);
        tableRdv = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tableRdv);
        scrollPane.setPreferredSize(new Dimension(900, 300));
        scrollPane.setMaximumSize(new Dimension(900, 300));
        panel.add(scrollPane);

        boutonRetour = createStyledButton("↩️ Retour");
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });

        chargerDonnees();
    }

    private void chargerDonnees() {
        List<RendezVous> liste = new RendezVousDAO().recupererTousLesRendezVous();
        model.setRowCount(0); // Réinitialise

        for (RendezVous r : liste) {
            model.addRow(new Object[]{
                    r.getId(),
                    r.getDate(),
                    r.getHeure(),
                    r.getIdPatient(),
                    r.getIdSpecialiste(),
                    r.getIdLieu(),
                    r.getNote()
            });
        }
    }

    private JButton createStyledButton(String text) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionRendezVousVue().setVisible(true));
    }
}
