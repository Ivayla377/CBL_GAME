import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Heart displayer.
 */
public class Heart {

    Image heart;


    public Heart() {
        initHeart();
    }
    
    public void initHeart() {
        loadHeart();
    }

    private void loadHeart() {
        ImageIcon ii = new ImageIcon("Images/heart.png");
        heart = ii.getImage();        
    }

   
}