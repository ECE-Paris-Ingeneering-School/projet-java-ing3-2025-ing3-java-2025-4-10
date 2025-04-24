package vue;

import controleur.RendezVousControleur;
import modele.RendezVous;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionRendezVousVue extends JFrame {

    private JTable tableRendezVous;
    private DefaultTableModel tableModel;
    private JButton boutonRetour;

    private final RendezVousControleur controleur;

    public GestionRendezVousVue() {
        setTitle("Gestion des rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        this.controleur = new RendezVousControleur();

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(900, 700));

        JLabel titre = new JLabel("Gestion des rendez-vous");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        tableRendezVous = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableRendezVous);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setMaximumSize(new Dimension(800, 300));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20));

        boutonRetour = createStyledButton("↩️ Retour");
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });

        chargerRendezVous();
    }

    private void chargerRendezVous() {
        List<RendezVous> liste = controleur.getTousLesRendezVous();

        String[] colonnes = {"ID", "Patient", "Spécialiste", "Lieu", "Date", "Heure", "Note"};
        tableModel = new DefaultTableModel(colonnes, 0);

        for (RendezVous rdv : liste) {
            Object[] ligne = {
                    rdv.getId(),
                    rdv.getNomPatient(),
                    rdv.getNomSpecialiste(),
                    rdv.getNomLieu(),
                    rdv.getDate(),
                    rdv.getHeure(),
                    rdv.getNote()
            };
            tableModel.addRow(ligne);
        }

        tableRendezVous.setModel(tableModel);
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
