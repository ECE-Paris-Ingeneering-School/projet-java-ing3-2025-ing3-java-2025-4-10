package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionSpecialistesVue extends JFrame {

    private JTextField champNom, champPrenom, champEmail, champSpecialite, champQualification;
    private JButton boutonAjouter, boutonSupprimer, boutonRetour;

    public GestionSpecialistesVue() {
        setTitle("Gestion des spécialistes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initialiserInterface();
    }

    private void initialiserInterface() {
        // === Fond bleu ciel ===
        JPanel fond = new JPanel(new GridBagLayout());
        fond.setBackground(new Color(200, 225, 255));

        // === Bloc blanc central ===
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)
        ));
        panel.setMaximumSize(new Dimension(600, 600));

        JLabel titre = new JLabel("Gestion des spécialistes");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        // Champs
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

        // Boutons
        boutonAjouter = createStyledButton("✅ Ajouter spécialiste");
        boutonSupprimer = createStyledButton("🗑️ Supprimer spécialiste");
        boutonRetour = createStyledButton("↩️ Retour");

        panel.add(boutonAjouter);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonSupprimer);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        // Actions
        boutonAjouter.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Spécialiste ajouté (simulation)");
        });

        boutonSupprimer.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Spécialiste supprimé (simulation)");
        });

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });
    }

    private JPanel createInput(String labelText, JTextField champ) {
        JPanel line = new JPanel();
        line.setLayout(new BoxLayout(line, BoxLayout.Y_AXIS));
        line.setBackground(Color.WHITE);
        line.setAlignmentX(Component.CENTER_ALIGNMENT);
        line.setMaximumSize(new Dimension(500, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        champ.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        champ.setAlignmentX(Component.LEFT_ALIGNMENT);

        line.add(label);
        line.add(champ);

        return line;
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
        SwingUtilities.invokeLater(() -> new GestionSpecialistesVue().setVisible(true));
    }
}
