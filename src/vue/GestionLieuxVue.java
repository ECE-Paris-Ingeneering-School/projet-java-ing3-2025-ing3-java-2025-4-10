package vue;

import javax.swing.*;
import java.awt.*;

public class GestionLieuxVue extends JFrame {

    private JTextField champAdresse, champVille, champCodePostal;
    private JButton boutonAjouter, boutonSupprimer, boutonRetour;

    public GestionLieuxVue() {
        setTitle("Gestion des lieux");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        champAdresse = new JTextField();
        champVille = new JTextField();
        champCodePostal = new JTextField();

        boutonAjouter = new JButton("Ajouter lieu");
        boutonSupprimer = new JButton("Supprimer lieu");
        boutonRetour = new JButton("Retour");

        panel.add(new JLabel("Adresse :"));      panel.add(champAdresse);
        panel.add(new JLabel("Ville :"));         panel.add(champVille);
        panel.add(new JLabel("Code postal :"));   panel.add(champCodePostal);
        panel.add(boutonAjouter);                 panel.add(boutonSupprimer);
        panel.add(new JLabel());                  panel.add(boutonRetour);

        add(panel);

        // Actions (pour l’instant simulées)
        boutonAjouter.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Lieu ajouté (simulation)");
        });

        boutonSupprimer.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Lieu supprimé (simulation)");
        });

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionLieuxVue().setVisible(true));
    }
}
