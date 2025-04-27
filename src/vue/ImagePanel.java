package vue;

import javax.swing.*;
import java.awt.*;
/**
 * Classe ImagePanel
 * Permet d'afficher une image de fond dans un JPanel.
 */
public class ImagePanel extends JPanel {
    private Image backgroundImage;
    public ImagePanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
