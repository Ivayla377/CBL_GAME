import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Stage one food:. the one and only, Ham-kaas Croissant!                        
 */
public class Croissant {
    
    Image croissant;

    public Croissant() {
        initCroissant();
    }

    public void initCroissant() {
        loadCroissant();
    }

    private void loadCroissant() {
        ImageIcon ii = new ImageIcon("Images/Croissant.jpg");
        croissant = ii.getImage();        
    }
}
