import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Background.
 */
public class Tenth {
    
    Image tenth;
    String tenthString;

    /**
     * Constructor.
     */
    public Tenth() {
        tenthString = "0";
        initTenth();
    }

    public void initTenth() {
        loadTenth();
    }

    /**
     * Load Image.
     */
    public void loadTenth() {
        ImageIcon ii = new ImageIcon("Images/" + tenthString + ".png");
        tenth = ii.getImage();
    }

    public void drawTenth(Graphics graphics) {
        graphics.drawImage(tenth, 536, 0, null);
    }
}
