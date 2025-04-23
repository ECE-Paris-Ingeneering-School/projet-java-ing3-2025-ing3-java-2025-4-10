package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionRendezVousVue extends JFrame {

    private JButton boutonRetour;

    public GestionRendezVousVue() {
        setTitle("Gestion des rendez-vous");
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
        panel.setMaximumSize(new Dimension(700, 400));

        JLabel titre = new JLabel("Gestion des rendez-vous (admin)");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titre);

        JTextArea infos = new JTextArea(
                "🛠️ Ici s'affichera la liste des rendez-vous à venir\n" +
                        "(Cette zone sera remplacée par une JTable ou une liste dynamique liée au DAO)"
        );
        infos.setEditable(false);
        infos.setFont(new Font("Monospaced", Font.PLAIN, 15));
        infos.setBackground(new Color(245, 245, 245));
        infos.setMargin(new Insets(10, 15, 10, 15));
        infos.setMaximumSize(new Dimension(600, 200));
        infos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(infos);

        panel.add(Box.createVerticalStrut(30));

        boutonRetour = createStyledButton("↩️ Retour");
        panel.add(boutonRetour);

        fond.add(panel);
        setContentPane(fond);

        // === Action ===
        boutonRetour.addActionListener(e -> {
            dispose();
            new AdminGestionVue().setVisible(true);
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
        SwingUtilities.invokeLater(() -> new GestionRendezVousVue().setVisible(true));
    }
}
