package vue;

import javax.swing.*;
import java.awt.*;

public class ReportingVue extends JFrame {

    private JButton boutonRetour;

    public ReportingVue() {
        setTitle("Statistiques & Reporting");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Statistiques de rendez-vous (simulation)", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        // Simule un graphique avec un label (à remplacer avec JFreeChart plus tard)
        JTextArea fakeChart = new JTextArea(
                "🔸 50% Cardiologie\n🔸 30% Dermatologie\n🔸 20% Dentaire"
        );
        fakeChart.setEditable(false);
        fakeChart.setFont(new Font("Monospaced", Font.PLAIN, 14));

        boutonRetour = new JButton("Retour");

        panel.add(label, BorderLayout.NORTH);
        panel.add(fakeChart, BorderLayout.CENTER);
        panel.add(boutonRetour, BorderLayout.SOUTH);

        add(panel);

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("admin").setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportingVue().setVisible(true));
    }
}
