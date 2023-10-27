import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Background.
 */
public class Digit {
    
    Image digit;
    String digitString;

    /**
     * Constructor.
     */
    public Digit() {
        digitString = "0";
        initDigit();
    }

    public void initDigit() {
        loadDigit();
    }

    /**
     * Load Image.
     */
    public void loadDigit() {
        ImageIcon ii = new ImageIcon("Images/" + digitString + ".png");
        digit = ii.getImage();
    }

    public void drawDigit(Graphics graphics) {
        graphics.drawImage(digit, 568, 0, null);
    }
}
