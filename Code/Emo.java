import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Stage 1 Enemy: Emo.
 */
public class Emo {

    Image emoImage;

    public Emo() {
        initEmoImage();
    }

    public void initEmoImage() {
        loadEmoImage();
    }

    public void loadEmoImage() {
        ImageIcon ii = new ImageIcon("Images/Emo.png");
        emoImage = ii.getImage();
    }
}
