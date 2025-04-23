package vue;

import dao.RendezVousDAO;
import modele.RendezVous;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoriqueVue extends JFrame {

    private final int idPatient;
    private JTable tableHistorique;
    private JButton boutonRetour;
    private DefaultTableModel model;

    public HistoriqueVue(int idPatient) {
        this.idPatient = idPatient;

        setTitle("Historique des rendez-vous");
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
        panel.setMaximumSize(new Dimension(800, 500));

        JLabel titre = new JLabel("Historique des rendez-vous");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titre);

        String[] colonnes = {"Date", "Heure", "ID Spécialiste", "ID Lieu", "Note"};
        model = new DefaultTableModel(colonnes, 0);
        tableHistorique = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tableHistorique);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        scrollPane.setMaximumSize(new Dimension(700, 300));
        panel.add(scrollPane);

        boutonRetour = createStyledButton("Retour");
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient", idPatient).setVisible(true);
        });

        chargerHistorique();
    }

    private void chargerHistorique() {
        List<RendezVous> liste = new RendezVousDAO().recupererRendezVousParPatient(idPatient);

        for (RendezVous r : liste) {
            Object[] ligne = {
                    r.getDate(),
                    r.getHeure(),
                    r.getIdSpecialiste(),
                    r.getIdLieu(),
                    r.getNote()
            };
            model.addRow(ligne);
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
        SwingUtilities.invokeLater(() -> new HistoriqueVue(1).setVisible(true));
    }
}
