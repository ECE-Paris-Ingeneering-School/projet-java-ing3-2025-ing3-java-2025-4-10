package vue;

import javax.swing.*;
import java.awt.*;

public class GestionRendezVousVue extends JFrame {

    private JButton boutonRetour;

    public GestionRendezVousVue() {
        setTitle("Gestion des rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea infos = new JTextArea("🛠️ Ici s'affichera la liste des rendez-vous à venir\n(à compléter avec DAO plus tard)");
        infos.setFont(new Font("Monospaced", Font.PLAIN, 14));
        infos.setEditable(false);

        boutonRetour = new JButton("Retour");

        panel.add(new JLabel("Gestion des RDV (admin)", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(infos, BorderLayout.CENTER);
        panel.add(boutonRetour, BorderLayout.SOUTH);

        add(panel);

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionRendezVousVue().setVisible(true));
    }
}
