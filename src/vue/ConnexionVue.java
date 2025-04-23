package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnexionVue extends JFrame {

    private JTextField champEmail;
    private JPasswordField champMotDePasse;
    private JButton boutonConnexion;
    private JButton boutonInscription;

    public ConnexionVue() {
        setTitle("Connexion utilisateur");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        // === FOND BLEU CIEL ===
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255)); // bleu ciel

        // === CONTENU CENTRAL ===
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)
        ));
        panel.setMaximumSize(new Dimension(450, 350));

        JLabel titre = new JLabel("Connexion");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JLabel labelEmail = new JLabel("Email :");
        champEmail = new JTextField();
        champEmail.setMaximumSize(new Dimension(300, 30));

        JLabel labelMotDePasse = new JLabel("Mot de passe :");
        champMotDePasse = new JPasswordField();
        champMotDePasse.setMaximumSize(new Dimension(300, 30));

        boutonConnexion = createStyledButton("🔐 Se connecter");
        boutonInscription = createStyledButton("📝 Créer un compte");

        // Espacement et ajout
        panel.add(titre);
        panel.add(labelEmail);
        panel.add(champEmail);
        panel.add(Box.createVerticalStrut(10));
        panel.add(labelMotDePasse);
        panel.add(champMotDePasse);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonConnexion);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonInscription);

        fond.add(panel);
        setContentPane(fond);

        // === Actions ===
        boutonConnexion.addActionListener(e -> {
            String email = champEmail.getText();
            String motDePasse = new String(champMotDePasse.getPassword());

            JOptionPane.showMessageDialog(this,
                    "Connexion de : " + email + "\n(Mot de passe : " + motDePasse + ")");
        });

        boutonInscription.addActionListener(e -> {
            dispose();
            new InscriptionVue().setVisible(true);
        });
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
        SwingUtilities.invokeLater(() -> new ConnexionVue().setVisible(true));
    }
}
