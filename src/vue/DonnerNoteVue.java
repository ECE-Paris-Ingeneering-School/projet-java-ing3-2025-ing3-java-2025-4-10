package vue;

import controleur.NoteControleur;
import modele.Note;

import javax.swing.*;
import java.awt.*;

public class DonnerNoteVue extends JFrame {

    private JComboBox<Integer> comboEtoiles;
    private JButton boutonValider, boutonRetour;
    private final int idRDV;
    private final int idUser;
    private final NoteControleur noteControleur;

    public DonnerNoteVue(int idRDV, int idUser) {
        this.idRDV = idRDV;
        this.idUser = idUser;
        this.noteControleur = new NoteControleur();

        setTitle("Noter votre rendez-vous");
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
        panel.setMaximumSize(new Dimension(500, 400));

        JLabel titre = new JLabel("Attribuez une note au rendez-vous");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        comboEtoiles = new JComboBox<>();
        for (int i = 1; i <= 5; i++) {
            comboEtoiles.addItem(i);
        }

        panel.add(createInput("Nombre d'étoiles :", comboEtoiles));
        panel.add(Box.createVerticalStrut(20));

        boutonValider = createStyledButton("Valider");
        boutonRetour = createStyledButton("Retour");

        panel.add(boutonValider);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonValider.addActionListener(e -> enregistrerNote());
        boutonRetour.addActionListener(e -> {
            dispose();
            new HistoriqueVue(idUser).setVisible(true);
        });
    }

    private void enregistrerNote() {
        int valeur = (int) comboEtoiles.getSelectedItem();

        Note note = new Note(0, idRDV, valeur);

        if (noteControleur.noteExisteDeja(idRDV)) {
            if (noteControleur.modifierNote(note)) {
                JOptionPane.showMessageDialog(this, "Note mise à jour !");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour de la note.");
            }
        } else {
            if (noteControleur.ajouterNote(note)) {
                JOptionPane.showMessageDialog(this, "Merci pour votre note !");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la note.");
            }
        }
        dispose();
        new HistoriqueVue(idUser).setVisible(true);
    }

    private JPanel createInput(String labelText, JComponent champ) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(400, 60));

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
        button.setMaximumSize(new Dimension(300, 45));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DonnerNoteVue(1, 1).setVisible(true));
    }
}
