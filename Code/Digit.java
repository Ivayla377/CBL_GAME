import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Background.
 */
public class Digit {
    
    Image digit;
    String digitString;
    String fileName;

    public Digit() {
        digitString = "0";
        fileName = "Images/" + digitString + ".png";
        initDigit();
    }

    public void initDigit() {
        loadDigit();
    }

    public void loadDigit() {
        ImageIcon ii = new ImageIcon(fileName);
        digit = ii.getImage();
    }

    public void drawDigit(Graphics graphics) {
        graphics.drawImage(digit, 568, 0, null);
    }
}
