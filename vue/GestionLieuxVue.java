package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionLieuxVue extends JFrame {

    private JTextField champAdresse, champVille, champCodePostal;
    private JButton boutonAjouter, boutonSupprimer, boutonRetour;

    public GestionLieuxVue() {
        setTitle("Gestion des lieux");
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
        panel.setMaximumSize(new Dimension(500, 500));

        JLabel titre = new JLabel("Gestion des lieux");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(titre);

        champAdresse = new JTextField();
        champVille = new JTextField();
        champCodePostal = new JTextField();

        panel.add(createInput("Adresse :", champAdresse));
        panel.add(createInput("Ville :", champVille));
        panel.add(createInput("Code postal :", champCodePostal));

        panel.add(Box.createVerticalStrut(20));

        boutonAjouter = createStyledButton("✅ Ajouter lieu");
        boutonSupprimer = createStyledButton("🗑️ Supprimer lieu");
        boutonRetour = createStyledButton("↩️ Retour");

        panel.add(boutonAjouter);
        panel.add(Box.createVerticalStrut(10));
        panel.add(boutonSupprimer);
        panel.add(Box.createVerticalStrut(20));
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        // === Actions ===
        boutonAjouter.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Lieu ajouté (simulation)");
        });

        boutonSupprimer.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Lieu supprimé (simulation)");
        });

        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
        });
    }

    private JPanel createInput(String labelText, JTextField field) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Color.WHITE);
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(450, 60));

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
        SwingUtilities.invokeLater(() -> new GestionLieuxVue().setVisible(true));
    }
}
