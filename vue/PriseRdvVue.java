package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PriseRdvVue extends JFrame {

    private JComboBox<String> comboSpecialiste;
    private JComboBox<String> comboLieu;
    private JSpinner champDate;
    private JComboBox<String> champHeure;
    private JButton boutonConfirmer;
    private JButton boutonRetour;

    public PriseRdvVue() {
        setTitle("Prendre un rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        // === Fond bleu ciel ===
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        // === Panel central ===
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)
        ));
        panel.setMaximumSize(new Dimension(500, 600));

        JLabel titre = new JLabel("Prise de rendez-vous");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        // === Données
        String[] specialites = {
                "Cardiologue", "Dentiste", "Dermatologue", "Gynécologue", "Pédiatre", "Psychiatre",
                "Ophtalmologue", "Gastro-entérologue", "Neurologue", "Urologue", "Rhumatologue",
                "ORL", "Endocrinologue", "Allergologue", "Oncologue", "Chirurgien", "Médecin généraliste",
                "Hématologue", "Néphrologue", "Radiologue", "Anesthésiste", "Médecin du sport",
                "Médecin du travail", "Sexologue", "Pneumologue", "Diabétologue", "Gériatre",
                "Immunologiste", "Interniste", "Médecin esthétique"
        };

        String[] villes = {
                "Paris", "Lyon", "Marseille", "Toulouse", "Nice", "Nantes", "Strasbourg",
                "Montpellier", "Bordeaux", "Lille", "Rennes", "Reims", "Le Havre",
                "Saint-Étienne", "Toulon", "Grenoble", "Dijon", "Angers", "Nîmes", "Villeurbanne",
                "Clermont-Ferrand", "Saint-Denis", "Le Mans", "Aix-en-Provence", "Brest",
                "Tours", "Amiens", "Limoges", "Annecy", "Perpignan", "Besançon", "Metz",
                "Orléans", "Rouen", "Mulhouse", "Caen", "Boulogne-Billancourt", "Nancy",
                "Argenteuil", "Montreuil", "Roubaix", "Avignon", "Poitiers", "Troyes",
                "Pau", "La Rochelle", "Antibes", "Calais", "Saint-Nazaire", "Drancy"
        };

        comboSpecialiste = new JComboBox<>(specialites);
        comboLieu = new JComboBox<>(villes);

        // === Date (JSpinner)
        SpinnerDateModel dateModel = new SpinnerDateModel();
        champDate = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(champDate, "dd/MM/yyyy");
        champDate.setEditor(dateEditor);
        champDate.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // === Heure (JComboBox)
        champHeure = new JComboBox<>(generateTimeSlots("08:00", "20:00"));
        champHeure.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        panel.add(createInputRow("Spécialiste :", comboSpecialiste));
        panel.add(createInputRow("Lieu :", comboLieu));
        panel.add(createInputRow("Date :", champDate));
        panel.add(createInputRow("Heure :", champHeure));

        panel.add(Box.createVerticalStrut(20));
        boutonConfirmer = createStyledButton("✅ Confirmer le rendez-vous");
        panel.add(boutonConfirmer);
        panel.add(Box.createVerticalStrut(10));
        boutonRetour = createStyledButton("↩️ Retour");
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        // === Actions
        boutonConfirmer.addActionListener(e -> {
            String specialiste = (String) comboSpecialiste.getSelectedItem();
            String lieu = (String) comboLieu.getSelectedItem();
            String date = new SimpleDateFormat("dd/MM/yyyy").format((Date) champDate.getValue());
            String heure = (String) champHeure.getSelectedItem();

            JOptionPane.showMessageDialog(this,
                    "RDV confirmé :\n" +
                            specialiste + " à " + lieu + "\nLe " + date + " à " + heure);
        });

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient").setVisible(true);
        });
    }

    private JPanel createInputRow(String labelText, JComponent input) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(400, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        input.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        input.setAlignmentX(Component.LEFT_ALIGNMENT);

        wrapper.add(label);
        wrapper.add(input);
        return wrapper;
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

        Color base = button.getBackground();
        Color hover = base.brighter();
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(base);
            }
        });

        return button;
    }

    private String[] generateTimeSlots(String start, String end) {
        ArrayList<String> slots = new ArrayList<>();
        int startHour = Integer.parseInt(start.split(":")[0]);
        int endHour = Integer.parseInt(end.split(":")[0]);

        for (int h = startHour; h <= endHour; h++) {
            slots.add(String.format("%02d:00", h));
            if (h != endHour) {
                slots.add(String.format("%02d:30", h));
            }
        }
        return slots.toArray(new String[0]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PriseRdvVue().setVisible(true));
    }
}
