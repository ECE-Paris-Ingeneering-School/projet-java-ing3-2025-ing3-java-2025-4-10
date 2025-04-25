package vue;

import controleur.LieuControleur;
import controleur.RendezVousControleur;
import controleur.SpecialisteControleur;
import modele.Lieu;
import modele.RendezVous;
import modele.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PriseRdvVue extends JFrame {

    private JComboBox<Specialiste> comboSpecialiste;
    private JComboBox<Lieu> comboLieu;
    private JComboBox<String> comboHeure;
    private JSpinner dateSpinner;
    private JTextField textMotif;
    private JButton boutonConfirmer;
    private JButton boutonRetour;

    private final SpecialisteControleur specialisteControleur;
    private final LieuControleur lieuControleur;
    private final RendezVousControleur rdvControleur;

    public PriseRdvVue() {
        setTitle("Prendre un rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        specialisteControleur = new SpecialisteControleur();
        lieuControleur = new LieuControleur();
        rdvControleur = new RendezVousControleur();

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(600, 600));

        JLabel titre = new JLabel("Réserver un créneau");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        comboSpecialiste = new JComboBox<>();
        comboLieu = new JComboBox<>();
        comboHeure = new JComboBox<>();
        textMotif = new JTextField();
        remplirHeures();

        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        panel.add(createInput("Spécialiste :", comboSpecialiste));
        panel.add(createInput("Lieu :", comboLieu));
        panel.add(createInput("Date :", dateSpinner));
        panel.add(createInput("Heure :", comboHeure));
        panel.add(createInput("Motif :", textMotif));
        panel.add(Box.createVerticalStrut(20));

        boutonConfirmer = createStyledButton("✅ Confirmer le rendez-vous");
        boutonRetour = createStyledButton("↩️ Retour");
        panel.add(boutonConfirmer);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonConfirmer.addActionListener(e -> confirmerRDV());
        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient",1).setVisible(true);
        });

        chargerSpecialistes();
        chargerLieux();
    }

    private void chargerSpecialistes() {
        comboSpecialiste.removeAllItems();
        List<Specialiste> liste = specialisteControleur.getTousLesSpecialistes();
        for (Specialiste s : liste) {
            comboSpecialiste.addItem(s);
        }
    }

    private void chargerLieux() {
        comboLieu.removeAllItems();
        List<Lieu> liste = lieuControleur.getTousLesLieux();
        for (Lieu l : liste) {
            comboLieu.addItem(l);
        }
    }

    private void remplirHeures() {
        for (int h = 8; h <= 19; h++) {
            comboHeure.addItem(String.format("%02d:00", h));
            comboHeure.addItem(String.format("%02d:30", h));
        }
    }

    private void confirmerRDV() {
        Specialiste specialiste = (Specialiste) comboSpecialiste.getSelectedItem();
        Lieu lieu = (Lieu) comboLieu.getSelectedItem();
        String heureStr = (String) comboHeure.getSelectedItem();
        String motif = textMotif.getText().trim();
        LocalDate date = LocalDate.ofInstant(((java.util.Date) dateSpinner.getValue()).toInstant(), java.time.ZoneId.systemDefault());
        LocalTime heure = LocalTime.parse(heureStr);

        if (specialiste == null || lieu == null || date == null || heureStr == null || motif == null) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        RendezVous rdv = new RendezVous();
        rdv.setIdSpecialiste(specialiste.getId());
        rdv.setIdLieu(lieu.getId());
        rdv.setIdPatient(1); // TODO: remonter id du patient connecté
        rdv.setDate(date.toString());
        rdv.setHeure(heure.toString());
        rdv.setMotif(motif);

        if (rdvControleur.ajouterRendezVous(rdv)) {
            JOptionPane.showMessageDialog(this, "Rendez-vous confirmé !");
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la confirmation.");
        }
    }

    private JPanel createInput(String labelText, JComponent champ) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(500, 70));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        champ.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        champ.setAlignmentX(Component.LEFT_ALIGNMENT);

        wrapper.add(label);
        wrapper.add(champ);
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
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PriseRdvVue().setVisible(true));
    }
}
