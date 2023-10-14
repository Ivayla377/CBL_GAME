import java.awt.*;
import javax.swing.ImageIcon;

public class GameOver {
    
    Image gameOver;


    public GameOver() {
        initGameOver();
    }
    
    public void initGameOver() {
        loadGameOver();
    }

    private void loadGameOver() {
        ImageIcon ii = new ImageIcon("Images/GameOver.png");
        gameOver = ii.getImage();        
    }
}
