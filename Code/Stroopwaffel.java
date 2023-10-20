import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Stage one food:. the one and only, Ham-kaas Croissant!                        
 */
public class Stroopwaffel {
    
    Image stroopWaffel;

    public Stroopwaffel() {
        initStroopWaffel();
    }

    public void initStroopWaffel() {
        loadStroopWaffel();
    }

    private void loadStroopWaffel() {
        ImageIcon ii = new ImageIcon("Images/Stroopwaffel.jpg");
        stroopWaffel = ii.getImage();        
    }
}
