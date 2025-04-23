package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InscriptionVue extends JFrame {

    private JTextField champNom;
    private JTextField champPrenom;
    private JTextField champEmail;
    private JPasswordField champMotDePasse;
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
        // === FOND BLEU CIEL ===
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255)); // Fond bleu ciel doux

        // === CONTENU CENTRAL (cadre blanc centré) ===
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

        // === CHAMPS ALIGNÉS ===
        panel.add(createInputRow("Nom :", champNom = new JTextField()));
        panel.add(createInputRow("Prénom :", champPrenom = new JTextField()));
        panel.add(createInputRow("Email :", champEmail = new JTextField()));
        panel.add(createInputRow("Mot de passe :", champMotDePasse = new JPasswordField()));

        // === BOUTONS ===
        panel.add(Box.createVerticalStrut(20));
        boutonInscription = createStyledButton("✅ S'inscrire");
        panel.add(boutonInscription);
        panel.add(Box.createVerticalStrut(10));
        boutonRetour = createStyledButton("↩️ Retour");
        panel.add(boutonRetour);

        // === Ajout au fond ===
        fond.add(panel);
        setContentPane(fond);

        // === ACTIONS ===
        boutonInscription.addActionListener(e -> {
            String nom = champNom.getText();
            String prenom = champPrenom.getText();
            String email = champEmail.getText();
            String motDePasse = new String(champMotDePasse.getPassword());

            JOptionPane.showMessageDialog(this,
                    "Compte créé pour : " + prenom + " " + nom + "\nEmail : " + email);
        });

        boutonRetour.addActionListener(e -> {
            dispose();
            new ConnexionVue().setVisible(true);
        });
    }

    // ✅ Crée une ligne verticale label au-dessus + champ large
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

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // champ s'étire
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        wrapper.add(label);
        wrapper.add(field);
        return wrapper;
    }

    // ✅ Boutons stylisés
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
