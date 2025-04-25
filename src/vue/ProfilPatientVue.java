package vue;

import dao.PatientDAO;
import modele.Patient;

import javax.swing.*;
import java.awt.*;

public class ProfilPatientVue extends JFrame {

    private final int idUser;
    private JTextField champNom, champPrenom;
    private JPasswordField champMotDePasse;
    private JButton boutonModifier, boutonRetour;

    public ProfilPatientVue(int idUser) {
        this.idUser = idUser;

        setTitle("Mon profil");
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
        panel.setMaximumSize(new Dimension(500, 500));

        JLabel titre = new JLabel("Mes informations");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titre);
        panel.add(Box.createVerticalStrut(30));

        champNom = new JTextField();
        champPrenom = new JTextField();
        champMotDePasse = new JPasswordField();

        panel.add(createInput("Nom :", champNom));
        panel.add(createInput("Prénom :", champPrenom));
        panel.add(createInput("Nouveau mot de passe :", champMotDePasse));

        panel.add(Box.createVerticalStrut(20));
        boutonModifier = createStyledButton("💾 Enregistrer");
        panel.add(boutonModifier);
        panel.add(Box.createVerticalStrut(10));
        boutonRetour = createStyledButton("↩️ Retour");
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonRetour.addActionListener(e -> {
            dispose();
            new MenuPrincipalVue("patient", idUser).setVisible(true);
        });

        boutonModifier.addActionListener(e -> modifierInfos());

        chargerDonnees();
    }

    private void chargerDonnees() {
        Patient p = new PatientDAO().recupererInfosPatient(idUser);
        if (p != null) {
            champNom.setText(p.getNom());
            champPrenom.setText(p.getPrenom());
        }
    }

    private void modifierInfos() {
        String nom = champNom.getText().trim();
        String prenom = champPrenom.getText().trim();
        String mdp = new String(champMotDePasse.getPassword()).trim();

        if (nom.isEmpty() || prenom.isEmpty() || mdp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        boolean ok = new PatientDAO().modifierInfosPatient(idUser, nom, prenom, mdp);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Profil mis à jour !");
            dispose();
            new MenuPrincipalVue("patient", idUser).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.");
        }
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
        SwingUtilities.invokeLater(() -> new ProfilPatientVue(1).setVisible(true));
    }
}
