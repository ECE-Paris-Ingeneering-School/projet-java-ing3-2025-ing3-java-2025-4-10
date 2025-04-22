package vue;

import javax.swing.*;
import java.awt.*;

public class GestionSpecialistesVue extends JFrame {

    private JTextField champNom, champPrenom, champEmail, champSpecialite, champQualification;
    private JButton boutonAjouter, boutonSupprimer, boutonRetour;

    public GestionSpecialistesVue() {
        setTitle("Gestion des spécialistes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        champNom = new JTextField();
        champPrenom = new JTextField();
        champEmail = new JTextField();
        champSpecialite = new JTextField();
        champQualification = new JTextField();

        boutonAjouter = new JButton("Ajouter spécialiste");
        boutonSupprimer = new JButton("Supprimer spécialiste");
        boutonRetour = new JButton("Retour");

        panel.add(new JLabel("Nom :"));             panel.add(champNom);
        panel.add(new JLabel("Prénom :"));          panel.add(champPrenom);
        panel.add(new JLabel("Email :"));           panel.add(champEmail);
        panel.add(new JLabel("Spécialité :"));      panel.add(champSpecialite);
        panel.add(new JLabel("Qualification :"));   panel.add(champQualification);
        panel.add(boutonAjouter);                   panel.add(boutonSupprimer);
        panel.add(new JLabel());                    panel.add(boutonRetour);

        add(panel);

        boutonAjouter.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Spécialiste ajouté (simulation)");
        });

        boutonSupprimer.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Spécialiste supprimé (simulation)");
        });

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionSpecialistesVue().setVisible(true));
    }
}
