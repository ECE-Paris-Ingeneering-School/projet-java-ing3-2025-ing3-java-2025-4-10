package vue;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoriqueVue extends JFrame {

    private JTable tableHistorique;
    private JButton boutonRetour;

    public HistoriqueVue() {
        setTitle("Historique des rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        String[] colonnes = {"Date", "Heure", "Spécialiste", "Lieu", "Note"};

        // Données simulées (à remplacer par DAO plus tard)
        Object[][] donnees = {
                {"15/04/2024", "14:00", "Dr. Dupont", "Paris", "Très bien"},
                {"02/03/2024", "10:30", "Dr. Martin", "Lyon", "Correct"},
                {"18/01/2024", "09:00", "Dr. Karim", "Marseille", ""}
        };

        DefaultTableModel model = new DefaultTableModel(donnees, colonnes);
        tableHistorique = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tableHistorique);

        boutonRetour = new JButton("Retour au menu");

        add(scrollPane, BorderLayout.CENTER);
        add(boutonRetour, BorderLayout.SOUTH);

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient").setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HistoriqueVue().setVisible(true));
    }
}
