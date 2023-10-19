import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Start Button Image.                       
 */
public class StartButton {
    
    Image startButton;

    public StartButton() {
        initStartButton();
    }

    public void initStartButton() {
        loadStartButton();
    }

    private void loadStartButton() {
        ImageIcon ii = new ImageIcon("Images/StartButton.png");
        startButton = ii.getImage();        
    }
}
