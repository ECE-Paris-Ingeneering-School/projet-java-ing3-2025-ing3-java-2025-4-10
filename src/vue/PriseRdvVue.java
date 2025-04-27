package vue;

// importation des classes nécessaires
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

/**
 * Classe de la vue pour la prise de rendez-vous.
 * Permet de choisir un spécialiste, un lieu, une date et une heure.
 */
public class PriseRdvVue extends JFrame { // classe prise de rdv qui hérite de jframe

    private JComboBox<Specialiste> comboSpecialiste; // liste déroulante de spécialistes
    private JComboBox<Lieu> comboLieu; // liste déroulante de lieux
    private JComboBox<String> comboHeure; // liste déroulante des heures
    private JSpinner dateSpinner; // spinner pour choisir la date
    private JTextField textMotif; // champ texte pour motif
    private JButton boutonConfirmer; // bouton confirmer rdv
    private JButton boutonRetour; // bouton retour

    private final SpecialisteControleur specialisteControleur; // controleur des spécialistes
    private final LieuControleur lieuControleur; // controleur des lieux
    private final RendezVousControleur rdvControleur; // controleur des rdv
    /**
     * Constructeur de la classe PriseRdvVue.
     * @param IdUser id de l'utilisateur
     */
    public PriseRdvVue(int IdUser) { // constructeur avec l'id du user
        setTitle("Prendre un rendez-vous"); // titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // ferme tout en quittant
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ouvrir en plein écran
        setLocationRelativeTo(null); // centrer la fenetre

        specialisteControleur = new SpecialisteControleur(); // initialiser controleurs
        lieuControleur = new LieuControleur();
        rdvControleur = new RendezVousControleur();

        initialiserInterface(IdUser); // construire interface
    }
    /**
     * Méthode pour initialiser l'interface graphique.
     * Crée le fond, le panneau central, les champs de saisie et les boutons.
     * @param IdUser id de l'utilisateur
     */
    private void initialiserInterface(int IdUser) { // méthode interface
        JPanel fond = new JPanel(new GridBagLayout()); // fond bleu
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel(); // bloc central blanc
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        panel.setMaximumSize(new Dimension(600, 600));

        JLabel titre = new JLabel("Réserver un créneau"); // titre
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        comboSpecialiste = new JComboBox<>();
        comboLieu = new JComboBox<>();
        comboHeure = new JComboBox<>();
        textMotif = new JTextField();
        remplirHeures(); // remplir les heures disponibles

        dateSpinner = new JSpinner(new SpinnerDateModel()); // date spinner
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        // ajout des champs
        panel.add(createInput("Spécialiste :", comboSpecialiste));
        panel.add(createInput("Lieu :", comboLieu));
        panel.add(createInput("Date :", dateSpinner));
        panel.add(createInput("Heure :", comboHeure));
        panel.add(createInput("Motif :", textMotif));
        panel.add(Box.createVerticalStrut(20));

        boutonConfirmer = createStyledButton("Confirmer le rendez-vous"); // bouton confirmer
        boutonRetour = createStyledButton("Retour"); // bouton retour
        panel.add(boutonConfirmer);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonConfirmer.addActionListener(e -> confirmerRDV(IdUser)); // action confirmer
        boutonRetour.addActionListener(e -> { // action retour
            dispose();
            new MenuPrincipalVue("patient", IdUser).setVisible(true);
        });

        comboSpecialiste.addActionListener(e -> remplirHeures());
        dateSpinner.addChangeListener(e -> remplirHeures());

        chargerSpecialistes(); // charger spécialistes
        chargerLieux(); // charger lieux
    }
    /**
     * Méthode pour charger tous les spécialistes dans la liste déroulante.
     */
    private void chargerSpecialistes() { // remplir combo des spécialistes
        comboSpecialiste.removeAllItems();
        List<Specialiste> liste = specialisteControleur.getTousLesSpecialistes();
        for (Specialiste s : liste) {
            comboSpecialiste.addItem(s);
        }
    }
    /**
     * Méthode pour charger tous les lieux dans la liste déroulante.
     */
    private void chargerLieux() { // remplir combo des lieux
        comboLieu.removeAllItems();
        List<Lieu> liste = lieuControleur.getTousLesLieux();
        for (Lieu l : liste) {
            comboLieu.addItem(l);
        }
    }
    /**
     * Méthode pour remplir la liste des heures disponibles en fonction du spécialiste et de la date sélectionnée.
     */
    private void remplirHeures() {
        comboHeure.removeAllItems(); // on vide la combo à chaque appel

        Specialiste specialiste = (Specialiste) comboSpecialiste.getSelectedItem();
        if (specialiste == null) return;

        LocalDate date = LocalDate.ofInstant(((java.util.Date) dateSpinner.getValue()).toInstant(), java.time.ZoneId.systemDefault());

        // Récupérer toutes les heures déjà prises pour ce spécialiste à cette date
        List<String> heuresPrises = rdvControleur.getHeuresPrises(specialiste.getId(), date);

        for (int h = 8; h <= 18; h++) {
            String h1 = String.format("%02d:00", h);
            String h2 = String.format("%02d:30", h);

            if (!heuresPrises.contains(h1)) {
                comboHeure.addItem(h1);
            } else {
                System.out.println("Exclu car déjà réservé : " + h1);
            }

            if (!heuresPrises.contains(h2)) {
                comboHeure.addItem(h2);
            } else {
                System.out.println("Exclu car déjà réservé : " + h2);
            }
        }
    }
    /**
     * Méthode pour confirmer le rendez-vous.
     * Récupère les informations saisies et les enregistre dans la base de données.
     * @param IdUser id de l'utilisateur
     */
    private void confirmerRDV(int IdUser) { // méthode confirmer le rdv
        Specialiste specialiste = (Specialiste) comboSpecialiste.getSelectedItem();
        Lieu lieu = (Lieu) comboLieu.getSelectedItem();
        String heureStr = (String) comboHeure.getSelectedItem();
        String motif = textMotif.getText().trim();
        LocalDate date = LocalDate.ofInstant(((java.util.Date) dateSpinner.getValue()).toInstant(), java.time.ZoneId.systemDefault());
        LocalTime heure = LocalTime.parse(heureStr);

        if (specialiste == null || lieu == null || date == null || heureStr == null || motif.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        RendezVous rdv = new RendezVous(); // création du rdv
        rdv.setIdSpecialiste(specialiste.getId());
        rdv.setIdLieu(lieu.getId());
        rdv.setIdPatient(IdUser);
        rdv.setDate(date.toString());
        rdv.setHeure(heure.toString());
        rdv.setMotif(motif);
        rdv.setDisponibilite(1);

        if (rdvControleur.ajouterRendezVous(rdv)) { // si ok
            remplirHeures();
            JOptionPane.showMessageDialog(this, "Rendez-vous confirmé !");
        } else { // sinon erreur
            JOptionPane.showMessageDialog(this, "Erreur lors de la confirmation.");
        }
    }
    /**
     * Méthode pour créer une ligne de saisie avec un label et un champ.
     * @param labelText texte du label
     * @param champ champ à ajouter
     * @return JPanel contenant le label et le champ
     */
    private JPanel createInput(String labelText, JComponent champ) { // méthode pour une ligne label + champ
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
    /**
     * Méthode pour créer un bouton stylisé.
     * @param text texte du bouton
     * @return JButton stylisé
     */
    private JButton createStyledButton(String text) { // méthode pour bouton stylisé
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
}
