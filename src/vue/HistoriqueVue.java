package vue;

import controleur.RendezVousControleur;
import modele.RendezVous;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoriqueVue extends JFrame {

    private JTable tableHistorique;
    private JButton boutonRetour;
    private DefaultTableModel tableModel;

    private final RendezVousControleur controleur;

    public HistoriqueVue() {
        setTitle("Historique des rendez-vous");
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
        panel.setMaximumSize(new Dimension(900, 600));

        JLabel titre = new JLabel("Historique de vos rendez-vous");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        String[] colonnes = {"Date", "Heure", "Spécialiste", "Lieu", "Motif"};
        tableModel = new DefaultTableModel(colonnes, 0);
        tableHistorique = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableHistorique);
        scrollPane.setPreferredSize(new Dimension(800, 250));
        scrollPane.setMaximumSize(new Dimension(800, 250));
        panel.add(scrollPane);

        boutonRetour = createStyledButton("Retour au menu");
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient",1).setVisible(true);
        });

        chargerHistorique();
    }

    private void chargerHistorique() {
        int idPatientSimule = 0; // À remplacer par l'ID réel du patient connecté
        List<RendezVous> historique = controleur.getRendezVousParPatient(idPatientSimule);

        for (RendezVous rdv : historique) {
            tableModel.addRow(new Object[]{
                    rdv.getDate(),
                    rdv.getHeure(),
                    rdv.getIdSpecialiste(),
                    rdv.getIdLieu(),
                    rdv.getMotif()
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
        button.setMaximumSize(new Dimension(350, 45));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HistoriqueVue().setVisible(true));
    }
}
