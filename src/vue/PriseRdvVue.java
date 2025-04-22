package vue;

import javax.swing.*;
import java.awt.*;

public class PriseRdvVue extends JFrame {

    private JComboBox<String> comboSpecialiste;
    private JComboBox<String> comboLieu;
    private JTextField champDate;
    private JTextField champHeure;
    private JButton boutonConfirmer;
    private JButton boutonRetour;

    public PriseRdvVue() {
        setTitle("Prendre un rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centre la fenêtre

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Champs simulés
        comboSpecialiste = new JComboBox<>(new String[]{"Cardiologue", "Dentiste", "Dermatologue"});
        comboLieu = new JComboBox<>(new String[]{"Paris", "Lyon", "Marseille"});

        champDate = new JTextField("JJ/MM/AAAA");
        champHeure = new JTextField("HH:MM");

        boutonConfirmer = new JButton("Confirmer le rendez-vous");
        boutonRetour = new JButton("Retour");

        panel.add(new JLabel("Spécialiste :"));
        panel.add(comboSpecialiste);
        panel.add(new JLabel("Lieu :"));
        panel.add(comboLieu);
        panel.add(new JLabel("Date :"));
        panel.add(champDate);
        panel.add(new JLabel("Heure :"));
        panel.add(champHeure);
        panel.add(boutonConfirmer);
        panel.add(boutonRetour);

        add(panel);

        // Action des boutons
        boutonConfirmer.addActionListener(e -> {
            String specialiste = (String) comboSpecialiste.getSelectedItem();
            String lieu = (String) comboLieu.getSelectedItem();
            String date = champDate.getText();
            String heure = champHeure.getText();

            JOptionPane.showMessageDialog(this,
                    "RDV confirmé :\n" +
                            specialiste + " à " + lieu + "\nLe " + date + " à " + heure);
        });

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient").setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PriseRdvVue().setVisible(true));
    }
}
