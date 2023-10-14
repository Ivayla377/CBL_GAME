import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * Enemy class with standard data for each enemy object.
 */
public class Enemy {
    Random rand = new Random();
    public int enemyX = rand.nextInt(6) * 100;
    public int enemyY = 40;
    public int speed = 4;

    Image enemy;

    public Enemy() {
        initEnemy();
    }

    public void initEnemy() {
        loadEnemy();
    }

    private void loadEnemy() {
        ImageIcon ii = new ImageIcon("Images/Emo.png");
        enemy = ii.getImage();        
    }

    
}
