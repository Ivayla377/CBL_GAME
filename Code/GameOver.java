import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Game Over screen.
 */
public class GameOver {
    
    Image gameOver;
    Image restartText;


    /**
     * Constructor.
     */
    public GameOver() {
        initGameOver();
        initRestartText();
    }
    
    public void initGameOver() {
        loadGameOver();
    }

    private void loadGameOver() {
        ImageIcon ii = new ImageIcon("Images/GameOver.png");
        gameOver = ii.getImage();        
    }

    public void initRestartText() {
        loadRestartText();
    }

    private void loadRestartText() {
        ImageIcon ii = new ImageIcon("Images/restart_text.png");
        restartText = ii.getImage();        
    }
}
