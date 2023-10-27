import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Background.
 */
public class Hundred {
    
    Image hundred;
    String hundredString;

    /**
     * Constructor.
     */
    public Hundred() {
        hundredString = "0";
        initHundred();
    }

    public void initHundred() {
        loadHundred();
    }

    /**
     * Load Image.
     */
    public void loadHundred() {
        ImageIcon ii = new ImageIcon("Images/" + hundredString + ".png");
        hundred = ii.getImage();
    }

    public void drawHundred(Graphics graphics) {
        graphics.drawImage(hundred, 504, 0, null);
    }
}
