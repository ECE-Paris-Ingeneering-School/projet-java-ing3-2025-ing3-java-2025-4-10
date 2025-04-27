package vue;

import controleur.RendezVousControleur;
import modele.RendezVous;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class HistoriqueVue extends JFrame {

    private JTable tableHistorique;
    private JButton boutonRetour;
    private DefaultTableModel tableModel;

    private final RendezVousControleur controleur;
    private final int idUser;

    public HistoriqueVue(int idUser) {
        this.idUser = idUser;
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

        String[] colonnes = {"ID RDV", "Date", "Heure", "Spécialiste", "Lieu", "Motif"};
        tableModel = new DefaultTableModel(colonnes, 0);
        tableHistorique = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableHistorique.removeColumn(tableHistorique.getColumnModel().getColumn(0)); // cacher ID RDV

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
            new MenuPrincipalVue("patient", idUser).setVisible(true);
        });

        tableHistorique.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tableHistorique.getSelectedRow() != -1) {
                    int modelRow = tableHistorique.convertRowIndexToModel(tableHistorique.getSelectedRow());
                    int idRDV = (int) tableModel.getValueAt(modelRow, 0);
                    new DonnerNoteVue(idRDV, idUser).setVisible(true);
                    dispose();
                }
            }
        });

        chargerHistorique();
    }

    private void chargerHistorique() {
        List<RendezVous> historique = controleur.getRendezVousParPatient(idUser);

        for (RendezVous rdv : historique) {
            tableModel.addRow(new Object[]{
                    rdv.getId(),
                    rdv.getDate(),
                    rdv.getHeure(),
                    rdv.getIdSpecialiste(), // Remplacer par nom réel si besoin
                    rdv.getIdLieu(),        // Remplacer par lieu réel si besoin
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
        SwingUtilities.invokeLater(() -> new HistoriqueVue(1).setVisible(true));
    }
}
