package vue; 

// importation des classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe MenuPrincipalVue
 * Permet d'afficher le menu principal de l'application.
 */
public class ReportingVue extends JFrame { // classe reporting vue qui hérite de jframe

    private JButton boutonRetour; // bouton retour

    public ReportingVue() { // constructeur
        setTitle("Statistiques & Reporting"); // titre de la fenetre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // fermer tout à la fermeture
        setExtendedState(JFrame.MAXIMIZED_BOTH); // ouverture plein écran
        setLocationRelativeTo(null); // centrer

        initialiserInterface(); // création de l'interface
    }

    private void initialiserInterface() { // méthode pour dessiner l'interface
        JPanel fond = new JPanel(new GridBagLayout()); // fond bleu ciel
        fond.setBackground(new Color(200, 225, 255));

        JPanel panel = new JPanel(); // panneau central blanc
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(40, 60, 40, 60)
        ));
        panel.setMaximumSize(new Dimension(700, 700)); // dimensions max du panel

        JLabel label = new JLabel("Répartition des rendez-vous par spécialité"); // titre
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // statistiques simulées dans une textarea
        JTextArea fakeChart = new JTextArea(
                "📊 Statistiques générales :\n\n" +
                        "Médecin généraliste        18%\n" +
                        "Cardiologue                12%\n" +
                        "Dentiste                   10%\n" +
                        "Dermatologue               8%\n" +
                        "Gynécologue                7%\n" +
                        "Pédiatre                   6%\n" +
                        "Ophtalmologue              5%\n" +
                        "Psychiatre                 4%\n" +
                        "Gastro-entérologue         3%\n" +
                        "Neurologue                 3%\n" +
                        "ORL                        2%\n" +
                        "Urologue                   2%\n" +
                        "Rhumatologue               2%\n" +
                        "Endocrinologue             2%\n" +
                        "Pneumologue                2%\n" +
                        "Oncologue                  2%\n" +
                        "Anesthésiste               1%\n" +
                        "Allergologue               1%\n" +
                        "Sexologue                  1%\n" +
                        "Médecin du sport           1%\n" +
                        "Radiologue                 1%\n" +
                        "Néphrologue                0.5%\n" +
                        "Hématologue                0.5%\n" +
                        "Gériatre                   0.5%\n" +
                        "Médecin du travail         0.5%\n" +
                        "Diabétologue               0.5%\n" +
                        "Interniste                 0.5%\n" +
                        "Immunologiste              0.3%\n" +
                        "Médecin esthétique         0.2%"
        );
        fakeChart.setEditable(false); // non éditable
        fakeChart.setFont(new Font("Monospaced", Font.PLAIN, 15)); // police alignée
        fakeChart.setBackground(new Color(245, 245, 245)); // fond clair
        fakeChart.setMargin(new Insets(10, 15, 10, 15)); // marges internes
        fakeChart.setAlignmentX(Component.CENTER_ALIGNMENT);
        fakeChart.setMaximumSize(new Dimension(600, 600)); // limite la taille

        boutonRetour = createStyledButton("Retour au menu"); // bouton retour

        // ajout des éléments au panel
        panel.add(label);
        panel.add(fakeChart);
        panel.add(Box.createVerticalStrut(30));
        panel.add(boutonRetour);

        fond.add(panel); // ajout du panel blanc dans le fond bleu
        setContentPane(fond);

        boutonRetour.addActionListener(e -> { // action retour
            dispose();
            new MenuPrincipalVue("admin", 1).setVisible(true);
        });
    }

    private JButton createStyledButton(String text) { // méthode pour créer un bouton stylisé
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(33, 150, 243)); // bleu
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 45));
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        Color base = button.getBackground();
        Color hover = base.brighter();

        button.addMouseListener(new MouseAdapter() { // hover effet
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hover);
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(base);
            }
        });

        return button;
    }

    public static void main(String[] args) { // méthode pour tester seul
        SwingUtilities.invokeLater(() -> new ReportingVue().setVisible(true));
    }
}
