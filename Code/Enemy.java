import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

/**
 * Enemy class with standard data for each enemy object.
 */
public class Enemy {
    Random rand;
    public int lane;
    public int enemyX;
    public int enemyY;
    public int speed;


    Image enemy;
    Emo emo;


    /**
     * Constructor.
     */
    public Enemy() {
        rand = new Random();
        lane = rand.nextInt(6);
        enemyX = lane * 100;
        enemyY = rand.nextInt(-40, 100);
        speed = 4;
        emo = new Emo();
        enemy = emo.emoImage;
    }

    // public void initEnemyImage() {
    //     loadEnemyImage();
    // }

    // public void loadEnemyImage() {
    //     ImageIcon ii = new ImageIcon("Images/Emo.png");
    //     enemy = ii.getImage();
    // }

    public void resetLane() {
        lane = rand.nextInt(6);
    }

    public void resetX() {
        resetLane();
        enemyX = lane * 100;
    }

    public void resetY() {
        enemyY = rand.nextInt(-40, 100);
    }
    
}
