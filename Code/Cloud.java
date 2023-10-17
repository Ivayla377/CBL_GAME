import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Background.
 */
public class Cloud {
    
    Image cloud;

    public Cloud() {
        initCloud();
    }

    public void initCloud() {
        loadCloud();
    }

    public void loadCloud() {
        ImageIcon ii = new ImageIcon("Images/Cloud.jpg");
        cloud = ii.getImage();
    }
}
