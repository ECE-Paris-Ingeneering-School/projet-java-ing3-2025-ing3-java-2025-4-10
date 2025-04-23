package vue;

import dao.LieuDAO;
import dao.RendezVousDAO;
import dao.SpecialisteDAO;
import modele.Lieu;
import modele.RendezVous;
import modele.Specialiste;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PriseRdvVue extends JFrame {

    private final int idPatient;
    private JComboBox<Specialiste> comboSpecialiste;
    private JComboBox<Lieu> comboLieu;
    private JComboBox<String> comboHeure;
    private JSpinner champDate;
    private JButton boutonConfirmer, boutonRetour;

    public PriseRdvVue(int idPatient) {
        this.idPatient = idPatient;

        setTitle("Prendre un rendez-vous");
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
        panel.setMaximumSize(new Dimension(600, 500));

        JLabel titre = new JLabel("Prise de rendez-vous");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titre);

        comboSpecialiste = new JComboBox<>();
        comboLieu = new JComboBox<>();
        comboHeure = new JComboBox<>();
        remplirHeures();

        champDate = new JSpinner(new SpinnerDateModel());
        champDate.setEditor(new JSpinner.DateEditor(champDate, "dd/MM/yyyy"));

        panel.add(createInput("Spécialiste :", comboSpecialiste));
        panel.add(createInput("Lieu :", comboLieu));
        panel.add(createInput("Date :", champDate));
        panel.add(createInput("Heure :", comboHeure));

        boutonConfirmer = createStyledButton("Confirmer le rendez-vous");
        boutonRetour = createStyledButton("Retour");

        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonConfirmer);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient", idPatient).setVisible(true);
        });

        boutonConfirmer.addActionListener(e -> confirmerRDV());

        chargerSpecialistes();
        chargerLieux();
    }

    private void chargerSpecialistes() {
        List<Specialiste> liste = new SpecialisteDAO().recupererTousLesSpecialistes();
        for (Specialiste s : liste) comboSpecialiste.addItem(s);
    }

    private void chargerLieux() {
        List<Lieu> lieux = new LieuDAO().recupererTousLesLieux();
        for (Lieu l : lieux) comboLieu.addItem(l);
    }

    private void remplirHeures() {
        for (int h = 8; h <= 19; h++) {
            comboHeure.addItem(String.format("%02d:00", h));
            comboHeure.addItem(String.format("%02d:30", h));
        }
    }

    private void confirmerRDV() {
        Specialiste s = (Specialiste) comboSpecialiste.getSelectedItem();
        Lieu l = (Lieu) comboLieu.getSelectedItem();
        String heure = (String) comboHeure.getSelectedItem();
        LocalDate date = ((java.util.Date) champDate.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        if (s == null || l == null || date == null || heure == null) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        RendezVous rdv = new RendezVous(0, idPatient, s.getId(), l.getId(), date.toString(), heure, "");
        boolean success = new RendezVousDAO().ajouterRendezVous(rdv);

        if (success) {
            JOptionPane.showMessageDialog(this, "Rendez-vous confirmé !");
            dispose();
            new MenuPrincipalVue("patient", idPatient).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement.");
        }
    }

    private JPanel createInput(String labelText, JComponent champ) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(500, 60));

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
        SwingUtilities.invokeLater(() -> new PriseRdvVue(1).setVisible(true));
    }
}
