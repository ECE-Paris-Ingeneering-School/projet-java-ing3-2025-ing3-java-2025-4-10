package vue;

import controleur.LieuControleur;
import modele.Lieu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionLieuxVue extends JFrame {

    private JTextField champAdresse, champVille, champCodePostal;
    private JButton boutonAjouter, boutonSupprimer, boutonRetour;
    private JTable tableLieux;
    private DefaultTableModel tableModel;

    private final LieuControleur controleur;

    public GestionLieuxVue() {
        setTitle("Gestion des lieux");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        this.controleur = new LieuControleur();

        initialiserInterface();
    }

    private void initialiserInterface() {
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(900, 700));

        JLabel titre = new JLabel("Gestion des lieux");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titre);

        champAdresse = new JTextField();
        champVille = new JTextField();
        champCodePostal = new JTextField();

        panel.add(createInput("Adresse :", champAdresse));
        panel.add(createInput("Ville :", champVille));
        panel.add(createInput("Code postal :", champCodePostal));
        panel.add(Box.createVerticalStrut(20));

        boutonAjouter = createStyledButton("Ajouter lieu");
        boutonSupprimer = createStyledButton("Supprimer lieu");
        boutonRetour = createStyledButton("Retour");

        panel.add(boutonAjouter);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonSupprimer);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);
        panel.add(Box.createVerticalStrut(30));

        tableLieux = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableLieux);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        scrollPane.setMaximumSize(new Dimension(800, 200));
        panel.add(scrollPane);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });

        boutonAjouter.addActionListener(e -> ajouterLieu());

        boutonSupprimer.addActionListener(e -> supprimerLieu());

        chargerLieux();
    }

    private void chargerLieux() {
        List<Lieu> lieux = controleur.getTousLesLieux();

        String[] colonnes = {"ID", "Adresse", "Ville", "Code postal"};
        tableModel = new DefaultTableModel(colonnes, 0);

        for (Lieu l : lieux) {
            Object[] ligne = {
                    l.getId(),
                    l.getAdresse(),
                    l.getVille(),
                    l.getCodePostal()
            };
            tableModel.addRow(ligne);
        }

        tableLieux.setModel(tableModel);
    }

    private void ajouterLieu() {
        String adresse = champAdresse.getText().trim();
        String ville = champVille.getText().trim();
        String codePostal = champCodePostal.getText().trim();

        if (adresse.isEmpty() || ville.isEmpty() || codePostal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        Lieu lieu = new Lieu(0, adresse, ville, codePostal);

        if (controleur.ajouterLieu(lieu)) {
            JOptionPane.showMessageDialog(this, "Lieu ajouté avec succès.");
            viderChamps();
            chargerLieux();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout.");
        }
    }

    private void supprimerLieu() {
        int ligneSelectionnee = tableLieux.getSelectedRow();

        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un lieu à supprimer.");
            return;
        }

        int id = (int) tableModel.getValueAt(ligneSelectionnee, 0);

        if (controleur.supprimerLieuParId(id)) {
            JOptionPane.showMessageDialog(this, "Lieu supprimé.");
            chargerLieux();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression.");
        }
    }

    private void viderChamps() {
        champAdresse.setText("");
        champVille.setText("");
        champCodePostal.setText("");
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
        SwingUtilities.invokeLater(() -> new GestionLieuxVue().setVisible(true));
    }
}
