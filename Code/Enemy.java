import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * Enemy class with standard data for each enemy object.
 */
public class Enemy {
    Random rand = new Random();
    public int lane = rand.nextInt(6);
    public int enemyX = lane * 100;
    public int enemyY = 40;
    public int speed = 4;


    Image enemy;

    public Enemy() {
        initEnemy();
    }

    public void initEnemy() {
        loadEnemy();
    }

    public void resetLane() {
        lane = rand.nextInt(6);
    }

    public void resetY() {
        enemyY = rand.nextInt(40, 100);
    }

    public void resetX() {
        resetLane();
        enemyX = lane * 100;
    }

    private void loadEnemy() {
        ImageIcon ii = new ImageIcon("Images/Emo.png");
        enemy = ii.getImage();        
    }

    
}
