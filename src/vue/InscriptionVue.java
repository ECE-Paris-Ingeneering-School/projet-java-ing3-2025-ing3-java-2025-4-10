package vue;

import dao.PatientDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InscriptionVue extends JFrame {

    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champEmail;
    private JPasswordField champMotDePasse;
    private JTextField champAncien;
    private JButton boutonInscription;
    private JButton boutonRetour;

    public InscriptionVue() {
        setTitle("Création d'un compte patient");
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
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)
        ));
        panel.setMaximumSize(new Dimension(500, 500));

        JLabel titre = new JLabel("Créer un compte patient");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        panel.add(createInputRow("Nom :", champNom = new JTextField()));
        panel.add(createInputRow("Prénom :", champPrenom = new JTextField()));
        panel.add(createInputRow("Email :", champEmail = new JTextField()));
        panel.add(createInputRow("Ancien (0 si new patient 1 sinon) :", champAncien = new JTextField()));
        panel.add(createInputRow("Mot de passe :", champMotDePasse = new JPasswordField()));

        panel.add(Box.createVerticalStrut(20));
        boutonInscription = createStyledButton("✅ S'inscrire");
        boutonInscription = createStyledButton("S'inscrire");
        panel.add(boutonInscription);
        panel.add(Box.createVerticalStrut(10));
        boutonRetour = createStyledButton("↩️ Retour");
        boutonRetour = createStyledButton("Retour");
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        boutonInscription.addActionListener(e -> inscrire());
        boutonRetour.addActionListener(e -> {
            dispose();
            new ConnexionVue().setVisible(true);
        });
    }

    private void inscrire() {
        String nom = champNom.getText().trim();
        String prenom = champPrenom.getText().trim();
        String email = champEmail.getText().trim();
        String ancien = champAncien.getText().trim();
        String motDePasse = new String(champMotDePasse.getPassword()).trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty() || ancien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            return;
        }

        PatientDAO dao = new PatientDAO();

        if (dao.emailExisteDeja(email)) {
            JOptionPane.showMessageDialog(this, "Cet email est déjà utilisé.");
            return;
        }

        boolean success = dao.enregistrerPatient(nom, prenom, email, motDePasse, ancien);
        if (success) {
            JOptionPane.showMessageDialog(this, "Compte créé avec succès !");
            dispose();
            new ConnexionVue().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la création du compte.");
        }
    }

    private JPanel createInputRow(String labelText, JTextField field) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(400, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        wrapper.add(label);
        wrapper.add(field);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InscriptionVue().setVisible(true));
    }
}