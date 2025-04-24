package vue;

import controleur.SpecialisteControleur;
import modele.Specialiste;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionSpecialistesVue extends JFrame {

    private JTextField champNom, champPrenom, champEmail, champSpecialite, champQualification;
    private JButton boutonAjouter, boutonSupprimer, boutonRetour;
    private JTable tableSpecialistes;
    private DefaultTableModel tableModel;

    private final SpecialisteControleur controleur;

    public GestionSpecialistesVue() {
        setTitle("Gestion des spécialistes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        this.controleur = new SpecialisteControleur();

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(900, 750));

        JLabel titre = new JLabel("Gestion des spécialistes");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titre);

        champNom = new JTextField();
        champPrenom = new JTextField();
        champEmail = new JTextField();
        champSpecialite = new JTextField();
        champQualification = new JTextField();

        panel.add(createInput("Nom :", champNom));
        panel.add(createInput("Prénom :", champPrenom));
        panel.add(createInput("Email :", champEmail));
        panel.add(createInput("Spécialité :", champSpecialite));
        panel.add(createInput("Qualification :", champQualification));
        panel.add(Box.createVerticalStrut(20));

        boutonAjouter = createStyledButton("Ajouter spécialiste");
        boutonSupprimer = createStyledButton("Supprimer spécialiste");
        boutonRetour = createStyledButton("Retour");

        panel.add(boutonAjouter);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonSupprimer);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);
        panel.add(Box.createVerticalStrut(30));

        tableSpecialistes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableSpecialistes);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        scrollPane.setMaximumSize(new Dimension(800, 200));
        panel.add(scrollPane);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });

        boutonAjouter.addActionListener(e -> ajouterSpecialiste());

        boutonSupprimer.addActionListener(e -> supprimerSpecialiste());

        chargerSpecialistes();
    }

    private void chargerSpecialistes() {
        List<Specialiste> liste = controleur.getTousLesSpecialistes();

        String[] colonnes = {"ID", "Nom", "Prénom", "Email", "Spécialité", "Qualification"};
        tableModel = new DefaultTableModel(colonnes, 0);

        for (Specialiste s : liste) {
            Object[] ligne = {
                    s.getId(),
                    s.getNom(),
                    s.getPrenom(),
                    s.getEmail(),
                    s.getSpecialite(),
                    s.getQualification()
            };
            tableModel.addRow(ligne);
        }

        tableSpecialistes.setModel(tableModel);
    }

    private void ajouterSpecialiste() {
        String nom = champNom.getText().trim();
        String prenom = champPrenom.getText().trim();
        String email = champEmail.getText().trim();
        String specialite = champSpecialite.getText().trim();
        String qualification = champQualification.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || specialite.isEmpty() || qualification.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        Specialiste specialiste = new Specialiste(0, nom, prenom, email, specialite, qualification);

        if (controleur.ajouterSpecialiste(specialiste)) {
            JOptionPane.showMessageDialog(this, "Spécialiste ajouté avec succès.");
            viderChamps();
            chargerSpecialistes();
        } else {
            JOptionPane.showMessageDialog(this, "Échec de l'ajout.");
        }
    }

    private void supprimerSpecialiste() {
        int ligneSelectionnee = tableSpecialistes.getSelectedRow();

        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un spécialiste à supprimer.");
            return;
        }

        int id = (int) tableModel.getValueAt(ligneSelectionnee, 0);

        if (controleur.supprimerSpecialisteParId(id)) {
            JOptionPane.showMessageDialog(this, "Spécialiste supprimé.");
            chargerSpecialistes();
        } else {
            JOptionPane.showMessageDialog(this, "Échec de la suppression.");
        }
    }

    private void viderChamps() {
        champNom.setText("");
        champPrenom.setText("");
        champEmail.setText("");
        champSpecialite.setText("");
        champQualification.setText("");
    }

    private JPanel createInput(String labelText, JTextField champ) {
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
        SwingUtilities.invokeLater(() -> new GestionSpecialistesVue().setVisible(true));
    }
}
