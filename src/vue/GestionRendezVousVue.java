package vue;

import controleur.RendezVousControleur;
import modele.RendezVous;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 * Classe de la vue pour la gestion des rendez-vous.
 * Permet d'afficher, ajouter et supprimer des rendez-vous.
 */
public class GestionRendezVousVue extends JFrame {

    private JTable tableRendezVous;
    private DefaultTableModel tableModel;
    private JButton boutonRetour, boutonAjouter, boutonSupprimer;
    private final RendezVousControleur controleur;

    /**
     * Constructeur de la classe GestionRendezVousVue.
     */
    public GestionRendezVousVue() {
        setTitle("Gestion des rendez-vous");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        this.controleur = new RendezVousControleur();

        initialiserInterface();
    }

    /**
     * Méthode pour initialiser l'interface graphique.
     */
    private void initialiserInterface() {
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(900, 700));

        JLabel titre = new JLabel("Gestion des rendez-vous");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        tableRendezVous = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableRendezVous);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setMaximumSize(new Dimension(800, 300));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20));

        boutonAjouter = createStyledButton("Ajouter un rendez-vous");
        boutonSupprimer = createStyledButton("Supprimer le rendez-vous sélectionné");
        boutonRetour = createStyledButton("Retour");

        panel.add(boutonAjouter);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonSupprimer);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });

        boutonAjouter.addActionListener(e -> ajouterRendezVous());
        boutonSupprimer.addActionListener(e -> supprimerRendezVous());

        chargerRendezVous();
    }

    /**
     * Méthode pour charger tous les rendez-vous dans le tableau.
     */
    private void chargerRendezVous() {
        List<RendezVous> liste = controleur.getTousLesRendezVous();

        String[] colonnes = {"ID", "Patient", "Spécialiste", "Lieu", "Date", "Heure", "Note"};
        tableModel = new DefaultTableModel(colonnes, 0);

        for (RendezVous rdv : liste) {
            Object[] ligne = {
                    rdv.getId(),
                    rdv.getIdPatient(),
                    rdv.getIdSpecialiste(),
                    rdv.getIdLieu(),
                    rdv.getDate(),
                    rdv.getHeure(),
                    rdv.getMotif()
            };
            tableModel.addRow(ligne);
        }

        tableRendezVous.setModel(tableModel);
    }

    /**
     * Méthode pour ajouter un rendez-vous.
     */
    private void ajouterRendezVous() {
        // Fenêtres pour entrer les infos
        String idPatient = JOptionPane.showInputDialog(this, "ID Patient:");
        String idSpecialiste = JOptionPane.showInputDialog(this, "ID Spécialiste:");
        String idLieu = JOptionPane.showInputDialog(this, "ID Lieu:");
        String date = JOptionPane.showInputDialog(this, "Date (YYYY-MM-DD):");
        String heure = JOptionPane.showInputDialog(this, "Heure (HH:MM):");
        String motif = JOptionPane.showInputDialog(this, "Motif:");

        if (idPatient != null && idSpecialiste != null && idLieu != null && date != null && heure != null && motif != null) {
            RendezVous rdv = new RendezVous(0, Integer.parseInt(idPatient), Integer.parseInt(idSpecialiste), Integer.parseInt(idLieu), date, heure, motif);
            controleur.ajouterRendezVous(rdv);
            chargerRendezVous(); // Refresh table
        }
    }

    /**
     * Méthode pour supprimer un rendez-vous sélectionné.
     */
    private void supprimerRendezVous() {
        int ligneSelectionnee = tableRendezVous.getSelectedRow();
        if (ligneSelectionnee != -1) {
            int id = (int) tableModel.getValueAt(ligneSelectionnee, 0); // ID en première colonne
            controleur.supprimerRendezVous(id);
            chargerRendezVous(); // Refresh table
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un rendez-vous à supprimer.");
        }
    }
    /**
     * Méthode pour créer un bouton stylisé.
     * @param text Le texte à afficher sur le bouton.
     * @return Le bouton stylisé.
     */
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
}