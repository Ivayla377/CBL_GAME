import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Semi-colon for clock.
 */
public class SemiColon {

    Image semiColon;

    public SemiColon() {
        initSemiColon();
    }

    public void initSemiColon() {
        loadSemiColon();
    }

    public void loadSemiColon() {
        ImageIcon ii = new ImageIcon("Images/SemiColon.png");
        semiColon = ii.getImage();
    }
}
