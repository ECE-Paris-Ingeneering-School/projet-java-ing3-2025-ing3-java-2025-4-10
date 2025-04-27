package vue; // package vue pour les interfaces utilisateur

import dao.UserDAO; // importation du dao utilisateur
import dao.UserDAO.Utilisateur; // importation de la classe interne utilisateur
import javax.swing.*; // composants swing
import java.awt.*; // layout et composants graphiques

public class ConnexionVue extends JFrame { // classe connexionvue qui hérite de jframe

    private JTextField champEmail; // champ pour l'email
    private JPasswordField champMotDePasse; // champ pour le mot de passe
    private JButton boutonConnexion; // bouton de connexion
    private JButton boutonInscription; // bouton d'inscription

    public ConnexionVue() { // constructeur
        setTitle("Connexion utilisateur"); // titre de la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE); // ferme complètement l'application
        setSize(400, 250); // taille de la fenêtre
        setLocationRelativeTo(null); // centre la fenêtre

        initialiserInterface(); // appel de la méthode pour construire l'interface
    }

    private void initialiserInterface() { // construction de l'interface
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10)); // grille 4x2 avec espaces

        JLabel labelEmail = new JLabel("Email :"); // label pour email
        champEmail = new JTextField(); // champ de saisie pour email

        JLabel labelMotDePasse = new JLabel("Mot de passe :"); // label pour mot de passe
        champMotDePasse = new JPasswordField(); // champ de saisie pour mot de passe

        boutonConnexion = new JButton("Se connecter"); // bouton pour se connecter
        boutonInscription = new JButton("Créer un compte"); // bouton pour créer un compte

        panel.add(labelEmail);         panel.add(champEmail); // première ligne
        panel.add(labelMotDePasse);    panel.add(champMotDePasse); // deuxième ligne
        panel.add(boutonConnexion);    panel.add(boutonInscription); // troisième ligne

        add(panel); // ajout du panel à la fenêtre

        boutonConnexion.addActionListener(e -> { // action du bouton connexion
            String email = champEmail.getText().trim(); // récupérer l'email saisi
            String mdp = new String(champMotDePasse.getPassword()).trim(); // récupérer le mot de passe saisi

            if (email.isEmpty() || mdp.isEmpty()) { // si champs vides
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs."); // afficher message
                return; // arrêter l'action
            }

            UserDAO dao = new UserDAO(); // création du dao utilisateur
            Utilisateur utilisateur = dao.verifierConnexion(email, mdp); // tentative de connexion

            if (utilisateur != null) { // si utilisateur trouvé
                dispose(); // fermer la fenêtre actuelle
                new MenuPrincipalVue(utilisateur.role, utilisateur.id).setVisible(true); // ouvrir menu principal
                System.out.println("ID : " + utilisateur.id); // afficher id dans la console
            } else { // sinon
                JOptionPane.showMessageDialog(this, "Identifiants incorrects."); // afficher erreur
            }
        });

        boutonInscription.addActionListener(e -> { // action du bouton inscription
            dispose(); // fermer la fenêtre actuelle
            new InscriptionVue().setVisible(true); // ouvrir la vue inscription
        });
    }

    public static void main(String[] args) { // méthode principale pour tester
        SwingUtilities.invokeLater(() -> new ConnexionVue().setVisible(true)); // lancer la vue connexion
    }
}
